package com.emat.reapi.api.dto.clienttestdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ClientTestSubmissionDto(
        @NotBlank(message = "Field submissionId can't be empty!")
        @Size(max = 255, message = "To long submissionId. Maximum size of submissionId filed is 255 characters.")
        String submissionId,

        @NotBlank(message = "Field pubicToken can't be empty!")
        @Size(max = 255, message = "To long pubicToken. Maximum size of pubicToken filed is 255 characters.")
        String pubicToken,

        @NotEmpty(message = "Client answers list can't be empty")
        List<ClientTestAnswerDto> clientTestAnswers
) {
}
