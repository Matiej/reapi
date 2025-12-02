package com.emat.reapi.profileanalysis.infra;

import com.emat.reapi.profileanalysis.domain.DominantCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DominantCategoryDocument {
    @Field("categoryId")
    private String categoryId;

    @Field("balanceIndex")
    private double balanceIndex;

    @Field("why")
    private String why;


    public static DominantCategoryDocument from(DominantCategory dominantCategory) {
        if (dominantCategory == null) return null;
        var doc = new DominantCategoryDocument();
        doc.setCategoryId(dominantCategory.getCategoryId());
        doc.setBalanceIndex(dominantCategory.getBalanceIndex());
        doc.setWhy(dominantCategory.getWhy());
        return doc;
    }

    public DominantCategory toDomain() {
        return DominantCategory.builder()
                .categoryId(categoryId)
                .balanceIndex(balanceIndex)
                .why(why)
                .build();
    }
}
