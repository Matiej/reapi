package com.emat.reapi.profiler.infra;

import com.emat.reapi.profiler.domain.report.ClientSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSummaryDocument {
    @Field("keyThemes")
    private List<String> keyThemes;

    @Field("dominantCategories")
    private List<DominantCategoryDocument> dominantCategories;

    @Field("overallNarrativePl")
    private String overallNarrativePl;


    public static ClientSummaryDocument from(ClientSummary clientSummary) {
        if (clientSummary == null) return null;
        var doc = new ClientSummaryDocument();
        doc.setKeyThemes(clientSummary.getKeyThemes());

        if (clientSummary.getDominantCategories() != null) {
            doc.setDominantCategories(
                    clientSummary.getDominantCategories().stream()
                            .map(DominantCategoryDocument::from)
                            .collect(Collectors.toList())
            );
        }
        doc.setOverallNarrativePl(clientSummary.getOverallNarrativePl());
        return doc;
    }

    public ClientSummary toDomain() {
        return ClientSummary.builder()
                .keyThemes(keyThemes)
                .dominantCategories(
                        dominantCategories != null
                                ? dominantCategories.stream().map(DominantCategoryDocument::toDomain).collect(Collectors.toList())
                                : null
                )
                .overallNarrativePl(overallNarrativePl)
                .build();
    }
}
