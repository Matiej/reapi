package com.emat.reapi.statement.infra;

import com.emat.reapi.statement.domain.StatementCategory;
import com.emat.reapi.statement.domain.StatementDefinition;
import com.emat.reapi.statement.domain.StatementType;
import com.emat.reapi.statement.domain.StatementTypeDefinition;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatementDefinitionsDictionary {

    private StatementDefinitionsDictionary() {
    }

    public static final List<StatementDefinition> ALL = List.of(
            // 1. Strażniczka Braku
            new StatementDefinition(
                    "1",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_yJDOep",
                    List.of(
                            new StatementTypeDefinition(
                                    "e5499158-6198-4706-85c2-56839ec03937",
                                    StatementType.LIMITING,
                                    "Strata boli bardziej niż brak"
                            ),
                            new StatementTypeDefinition(
                                    "3e0c1d97-35f8-4925-a3d3-6ded2711df89",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć i czuć się z tym bezpiecznie"
                            )
                    )
            ),

            // 2. Strażniczka Braku
            new StatementDefinition(
                    "2",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_X4qjOd",
                    List.of(
                            new StatementTypeDefinition(
                                    "92d48101-c141-4de9-b3b6-f06039300600",
                                    StatementType.LIMITING,
                                    "Pieniądze się mnie nie trzymają, im więcej zarabiam, tym więcej wydaję"
                            ),
                            new StatementTypeDefinition(
                                    "d7f081ca-3a24-4685-8d7e-736df1211d5c",
                                    StatementType.SUPPORTING,
                                    "Pieniądze lubią przy mnie zostawać i rosnąć"
                            )
                    )
            ),

            // 3. Samowystarczalna Tarcza
            new StatementDefinition(
                    "3",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_8Ze9jY",
                    List.of(
                            new StatementTypeDefinition(
                                    "2c7c22a4-a191-4025-bb12-1d26b20fb82e",
                                    StatementType.LIMITING,
                                    "Nie potrzebuję nikogo, sama wszystko ogarnę"
                            ),
                            new StatementTypeDefinition(
                                    "65b15b7c-c3ca-4194-a2da-5ce9ac68a1d6",
                                    StatementType.SUPPORTING,
                                    "Mogę przyjmować wsparcie i nadal być niezależna"
                            )
                    )
            ),

            // 4. Samowystarczalna Tarcza
            new StatementDefinition(
                    "4",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_0eP4o0",
                    List.of(
                            new StatementTypeDefinition(
                                    "6fc98025-49a1-408a-b41a-7a0201001fdb",
                                    StatementType.LIMITING,
                                    "Cenię dawanie, ale nie umiem brać"
                            ),
                            new StatementTypeDefinition(
                                    "aed6b046-7de1-473e-bbbe-7bbf8dd589d6",
                                    StatementType.SUPPORTING,
                                    "Potrafię brać i dawać, czuję w tym równowagę"
                            )
                    )
            ),

            // 5. Zamrożona Wizjonerka
            new StatementDefinition(
                    "5",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_z7eVW8",
                    List.of(
                            new StatementTypeDefinition(
                                    "ea01af0b-243f-4fe4-8a42-36c1e32a1190",
                                    StatementType.LIMITING,
                                    "Nie ruszę, dopóki nie będę perfekcyjnie przygotowana"
                            ),
                            new StatementTypeDefinition(
                                    "002f64cd-7496-4da7-a0cb-ac2a368c0bbf",
                                    StatementType.SUPPORTING,
                                    "Mogę zacząć teraz i uczyć się w trakcie"
                            )
                    )
            ),

            // 6. Zamrożona Wizjonerka
            new StatementDefinition(
                    "6",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_5Z2NEP",
                    List.of(
                            new StatementTypeDefinition(
                                    "dd39797c-1d6d-4b22-91b0-c162ceca57c7",
                                    StatementType.LIMITING,
                                    "Ciągle odkładam wiele rzeczy na później"
                            ),
                            new StatementTypeDefinition(
                                    "edb19492-997d-43dc-836e-edffd19f719e",
                                    StatementType.SUPPORTING,
                                    "Działam teraz, małymi krokami"
                            )
                    )
            ),

            // 7. Lojalna Dziedziczka
            new StatementDefinition(
                    "7",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_d9P7Qy",
                    List.of(
                            new StatementTypeDefinition(
                                    "fee5bbcf-17a7-479a-8569-c985b50db972",
                                    StatementType.LIMITING,
                                    "Nie mogę mieć więcej niż moi bliscy"
                            ),
                            new StatementTypeDefinition(
                                    "3fa2e7ad-aee4-49d6-bac0-dbeb3dcad28c",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć więcej i nadal czuć więź z tymi, których kocham"
                            )
                    )
            ),

            // 8. Lojalna Dziedziczka
            new StatementDefinition(
                    "8",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_Y4d2V6",
                    List.of(
                            new StatementTypeDefinition(
                                    "3864212a-2ec0-421d-a144-8c1534a8236a",
                                    StatementType.LIMITING,
                                    "Powtarzam schemat biedy po mojej rodzinie"
                            ),
                            new StatementTypeDefinition(
                                    "b6338899-fc39-4846-88b4-f1898974fc38",
                                    StatementType.SUPPORTING,
                                    "Mogę przełamać ten schemat i budować dostatek"
                            )
                    )
            ),

            // 9. Wycofana Liderka
            new StatementDefinition(
                    "9",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_D7eL0j",
                    List.of(
                            new StatementTypeDefinition(
                                    "cfa2c456-665c-4375-89bd-cb4941815e0e",
                                    StatementType.LIMITING,
                                    "Pieniądze przyciągają zazdrość"
                            ),
                            new StatementTypeDefinition(
                                    "0cfcdd2e-8942-4e9f-a465-bf8e63b437c0",
                                    StatementType.SUPPORTING,
                                    "Pieniądze mogą przyciągać dobre relacje i współpracę"
                            )
                    )
            ),

            // 10. Wycofana Liderka
            new StatementDefinition(
                    "10",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_l6RpVp",
                    List.of(
                            new StatementTypeDefinition(
                                    "53709400-e882-445c-bda8-0cfc3b02dbfd",
                                    StatementType.LIMITING,
                                    "Sukces finansowy to samotność"
                            ),
                            new StatementTypeDefinition(
                                    "7e20df45-8789-4bda-839e-6a10bbc16b57",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć sukces finansowy i bliskość"
                            )
                    )
            ),

            // 11. Zapracowana Perfekcjonistka
            new StatementDefinition(
                    "11",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_RDLO8p",
                    List.of(
                            new StatementTypeDefinition(
                                    "3e5615cd-195a-4e25-a938-030fe14d303f",
                                    StatementType.LIMITING,
                                    "Na pieniądze trzeba ciężko pracować i się poświęcać"
                            ),
                            new StatementTypeDefinition(
                                    "844d4721-eca8-4d3c-ba84-c3a86af83343",
                                    StatementType.SUPPORTING,
                                    "Mogę zarabiać pieniądze w lekki i przyjemny sposób"
                            )
                    )
            ),

            // 12. Zapracowana Perfekcjonistka
            new StatementDefinition(
                    "12",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_o2OJlX",
                    List.of(
                            new StatementTypeDefinition(
                                    "88a0a4c4-dff1-474e-b7ed-4e8e33a69e93",
                                    StatementType.LIMITING,
                                    "Zawsze muszę być produktywna"
                            ),
                            new StatementTypeDefinition(
                                    "ae798255-beb6-4a98-8aa8-ddda85f89565",
                                    StatementType.SUPPORTING,
                                    "Mam wartość także wtedy, gdy odpoczywam"
                            )
                    )
            ),

            // 13. Zatrzymana w Przyjmowaniu
            new StatementDefinition(
                    "13",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_GR0MY2",
                    List.of(
                            new StatementTypeDefinition(
                                    "98598178-7a46-407e-bb48-d43c2c9f0d88",
                                    StatementType.LIMITING,
                                    "Nie wolno brać pieniędzy za pracę duchową"
                            ),
                            new StatementTypeDefinition(
                                    "47c446bf-e5bb-44a8-8f85-dfd0e2f64676",
                                    StatementType.SUPPORTING,
                                    "Mogę z szacunkiem przyjmować zapłatę także za pracę duchową"
                            )
                    )
            ),

            // 14. Zatrzymana w Przyjmowaniu
            new StatementDefinition(
                    "14",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_O70R8p",
                    List.of(
                            new StatementTypeDefinition(
                                    "0b47c4aa-118c-4551-a304-f30747cede71",
                                    StatementType.LIMITING,
                                    "Jeśli coś dostaję, muszę się odwdzięczyć"
                            ),
                            new StatementTypeDefinition(
                                    "59284326-e618-42d6-b62d-5c3bc2e19307",
                                    StatementType.SUPPORTING,
                                    "Mogę przyjmować bez poczucia długu"
                            )
                    )
            ),

            // 15. Idealistka Skromności
            new StatementDefinition(
                    "15",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_Vz8rvE",
                    List.of(
                            new StatementTypeDefinition(
                                    "d0ee4f62-3ba7-4120-b861-cbb9da322959",
                                    StatementType.LIMITING,
                                    "Bieda jest cnotą"
                            ),
                            new StatementTypeDefinition(
                                    "39ff0f1f-6a39-4e1c-b8a3-e12ad11440f6",
                                    StatementType.SUPPORTING,
                                    "Dobrobyt też może być cnotą i narzędziem dobra"
                            )
                    )
            ),

            // 16. Idealistka Skromności
            new StatementDefinition(
                    "16",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_Pz0ovd",
                    List.of(
                            new StatementTypeDefinition(
                                    "8c2f5ac4-a421-43d7-9f50-93d9599b6cd9",
                                    StatementType.LIMITING,
                                    "Duchowość i pieniądze się wykluczają"
                            ),
                            new StatementTypeDefinition(
                                    "16fbbfe6-c87a-4105-a86f-33ca59e62564",
                                    StatementType.SUPPORTING,
                                    "Duchowość i pieniądze mogą iść w parze"
                            )
                    )
            )
    );

    private static final Map<String, StatementDefinition> BY_ID =
            ALL.stream().collect(Collectors.toUnmodifiableMap(
                    d -> d.getStatementId().trim().toLowerCase(),
                    Function.identity()
            ));

    public static StatementDefinition requireById(String statementId) {
        if (statementId == null) {
            throw new IllegalArgumentException("statementId is null");
        }
        StatementDefinition def = BY_ID.get(statementId.trim().toLowerCase());
        if (def == null) {
            throw new IllegalArgumentException("Unknown statementId: " + statementId);
        }
        return def;
    }

}
