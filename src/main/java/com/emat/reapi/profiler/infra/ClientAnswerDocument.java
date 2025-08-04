package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.AnsweredStatement;
import com.emat.reapi.profiler.domain.ClientAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("client_answers")
public class ClientAnswerDocument {
    @Id
    private String id;
    private String clientId;
    private List<AnsweredStatement> answeredStatementList;

    public ClientAnswerDocument(String clientId, List<AnsweredStatement> answeredStatementList) {
        this.clientId = clientId;
        this.answeredStatementList = answeredStatementList;
    }

    public static ClientAnswerDocument toDocument(ClientAnswer domain) {
        return new ClientAnswerDocument(domain.getClientId(), domain.getAnsweredStatementList());
    }

    public ClientAnswer toDomain() {
        return new ClientAnswer(clientId, answeredStatementList);
    }
}
