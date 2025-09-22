package com.emat.reapi.tally;

import com.emat.reapi.api.dto.ClientAnswerDto;
import com.emat.reapi.api.tally.TallyWebhookEvent;
import com.emat.reapi.statement.port.ClientAnswerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class TallyServiceImpl implements TallyService {
    private final ClientAnswerService clientAnswerService;

    @Override
    public Mono<Void> processTallyEvent(TallyWebhookEvent event) {
        ClientAnswerDto clientAnswerDto = TallyToDtoMapper.map(event);
        return clientAnswerService.saveClientAnswers(clientAnswerDto);
    }
}
