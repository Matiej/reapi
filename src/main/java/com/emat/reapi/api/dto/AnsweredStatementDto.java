package com.emat.reapi.api.dto;

import com.emat.reapi.profiler.domain.ClientStatement;
import com.emat.reapi.profiler.domain.StatementMapper;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnsweredStatementDto {
    @NotBlank(message = "statementId is required")
    private String statementId;
    private String key;
    private List<StatementDto> statementDtoList;

    public ClientStatement toDomain() {
        return StatementMapper.toDomain(this);
    }
}
