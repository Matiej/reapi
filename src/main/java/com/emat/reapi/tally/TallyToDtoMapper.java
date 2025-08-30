package com.emat.reapi.tally;

import com.emat.reapi.api.dto.AnsweredStatementDto;
import com.emat.reapi.api.dto.ClientAnswerDto;
import com.emat.reapi.api.dto.StatementDto;
import com.emat.reapi.api.tally.TallyWebhookEvent;
import com.emat.reapi.profiler.domain.StatementDefinition;
import com.emat.reapi.profiler.domain.StatementDefinitionsDictionary;
import com.emat.reapi.profiler.domain.StatementTypeDefinition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public final class TallyToDtoMapper {
    private TallyToDtoMapper() {
    }

    private static final Pattern QUESTION_NUMBER_PATTERN =
            Pattern.compile("^(\\d{1,3})\\)");

    public static ClientAnswerDto map(TallyWebhookEvent event) {
        var data = event.getData();
        String clientId = data.getRespondentId();
        String submissionId = data.getSubmissionId();
        String name = findValueByLabel(data.getFields(), "Imię");

        Map<String, AnsweredStatementDto> resultMap = new LinkedHashMap<>();

        for (var field : data.getFields()) {
            if (!"CHECKBOXES".equalsIgnoreCase(field.getType())) continue;
            if (!(field.getValue() instanceof Boolean)) continue;

            String label = field.getLabel();
            if (label == null) continue;

            Matcher matcher = QUESTION_NUMBER_PATTERN.matcher(label);
            if (!matcher.find()) continue;

            String statementId = matcher.group(1);
            StatementDefinition def;
            try {
                def = StatementDefinitionsDictionary.requireById(statementId);
            } catch (Exception ex) {
                log.warn("No definition for statementId={}", statementId);
                continue;
            }

            String fieldKey = field.getKey();
            String[] parts = fieldKey.split("_");
            String optionId = parts[parts.length - 1];
            boolean status = Boolean.TRUE.equals(field.getValue());

            StatementTypeDefinition matchedType = def.getStatementTypeDefinitions().stream()
                    .filter(opt -> opt.getKey().equals(optionId))
                    .findFirst()
                    .orElse(null);

            if (matchedType == null) {
                log.warn("OptionId {} nie pasuje do definicji dla statementId {}", optionId, statementId);
                continue;
            }

            AnsweredStatementDto dto = resultMap.computeIfAbsent(statementId, sid -> {
                List<StatementDto> initialList = def.getStatementTypeDefinitions().stream()
                        .map(opt -> new StatementDto(opt.getKey(), opt.getStatementDescription(), null))
                        .collect(Collectors.toList());
                return new AnsweredStatementDto(statementId, def.getStatementKey(),  initialList);
            });

            dto.getStatementDtoList().stream()
                    .filter(s -> s.statementKey().equals(matchedType.getKey()))
                    .findFirst()
                    .ifPresent(s -> {
                        int idx = dto.getStatementDtoList().indexOf(s);
                        dto.getStatementDtoList().set(idx,
                                new StatementDto(s.statementKey(), s.statementDescription(), status));
                    });
        }

        List<AnsweredStatementDto> answeredStatements = new ArrayList<>(resultMap.values());
        answeredStatements.sort(Comparator.comparingInt(dto -> Integer.parseInt(dto.getStatementId())));

        return new ClientAnswerDto(clientId, submissionId, name, data.getFormName(), answeredStatements);
    }

    private static String findValueByLabel(List<TallyWebhookEvent.Field> fields, String label) {
        if (fields == null) return null;
        for (var f : fields) {
            if (label.equalsIgnoreCase(f.getLabel())) {
                Object v = f.getValue();
                return v == null ? null : String.valueOf(v);
            }
        }
        return null;
    }
}
