package com.emat.reapi.api.tally;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TallyWebhookEvent {
    private String eventId;
    private String eventType;
    private String createdAt;
    private DataNode data;

    @Data
    public static class DataNode {
        private String responseId;
        private String submissionId;
        private String respondentId; // ← clientId
        private String formId;
        private String formName;
        private String createdAt;
        private List<Field> fields;
    }

    @Data
    public static class Field {
        private String key;
        private String label;
        private String type;
        Object value;
        private List<Option> options;
    }

    @Data
    public static class Option {
        private String id;
        private String text;
    }
}
