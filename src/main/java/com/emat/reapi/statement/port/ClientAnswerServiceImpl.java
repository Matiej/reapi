package com.emat.reapi.statement.port;

import com.emat.reapi.api.dto.ClientAnswerDto;
import com.emat.reapi.statement.domain.ClientAnswer;
import com.emat.reapi.statement.infra.ClientAnswerDocument;
import com.emat.reapi.statement.infra.ClientAnswerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
class ClientAnswerServiceImpl implements ClientAnswerService {
    private final ClientAnswerRepository clientAnswerRepository;

    @Override
    public Mono<Void> saveClientAnswers(ClientAnswerDto clientAnswer) {
        log.info("Saving client answers for clientId: {}", clientAnswer.clientId());
        ClientAnswer clientAnswerDomain = clientAnswer.toDomain();
        ClientAnswerDocument document = ClientAnswerDocument.toDocument(clientAnswerDomain);
        return clientAnswerRepository.save(document)
                .doOnSuccess(saved -> log.debug("Saved clients answers: {}", clientAnswer))
                .doOnError(error -> log.error("Error saving client answers for clientId: {}", clientAnswer.clientId(), error))
                .then();
    }

    @Override
    public Mono<List<ClientAnswer>> getAllAnsweredStatements() {
        log.info("Trying to retrieve all clients tests");
        return clientAnswerRepository.findAll()
                .map(ClientAnswerDocument::toDomain)
                .collectList()
                .doOnError(error -> log.error("Error retrieving client test answers"))
                .doOnSuccess(it -> log.info("Retrieved {} client tests", it.size())
                );
    }

    @Override
    public Mono<ClientAnswer> getAnsweredStatementBySubmissionId(String submissionId) {
        log.info("Trying to retrieve client test by submission Id: {}", submissionId);
        return clientAnswerRepository.findClientAnswerDocumentBySubmissionId(submissionId)
                .map(ClientAnswerDocument::toDomain)
                .doOnError(error -> log.error("Error retrieving client test answers"))
                .doOnSuccess(it -> log.info("Retrieved client test for submission Id: {}", it != null ? it.getSubmissionId() : "No submissions found for " + submissionId)
                );
    }

    @Override
    public Mono<ClientAnswer> getAnsweredStatementByClientId(String clientId) {
        log.info("Trying to retrieve client test by client Id: {}", clientId);
        return clientAnswerRepository.findClientAnswerDocumentByClientId(clientId)
                .map(ClientAnswerDocument::toDomain)
                .doOnError(error -> log.error("Error retrieving client test answers for client id: {}", clientId))
                .doOnSuccess(it -> log.info("Retrieved client test for client Id: {}", it.getClientId())
                );
    }
}
