package com.emat.reapi.api.dto;

import com.emat.reapi.profiler.domain.AnsweredStatement;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnsweredStatementDto {
    @NotBlank(message = "statementId is required")
    private String statementId;

    @Min(value = -2, message = "score must be >= -2")
    @Max(value = 2, message = "score must be <= 2")
    private int score;

    public AnsweredStatement toDomain() {
        return new AnsweredStatement(statementId, score);
    }

    public static AnsweredStatementDto toDto(AnsweredStatement domain) {
        return new AnsweredStatementDto(domain.getStatementId(), domain.getScore());
    }
}
