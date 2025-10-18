package com.emat.reapi.statement.infra;

import com.emat.reapi.statement.domain.StatementCategory;
import com.emat.reapi.statement.domain.StatementDefinition;
import com.emat.reapi.statement.domain.StatementType;
import com.emat.reapi.statement.domain.StatementTypeDefinition;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatementDefinitionsDictionary {

    private StatementDefinitionsDictionary() {
    }

    public static final List<StatementDefinition> MODESTY_IDEALIST = List.of(
            // 1
            new StatementDefinition(
                    "8",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_OGD08M",
                    List.of(
                            new StatementTypeDefinition(
                                    "70aa46f7-3015-4704-b2b6-752ab59c0843",
                                    StatementType.LIMITING,
                                    "Bieda jest cnotą."
                            ),
                            new StatementTypeDefinition(
                                    "90a73c9f-5aca-4cec-afb2-9ca123b6cd8b",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć pieniądze i nadal być dobrą osobą."
                            )
                    )
            ),
            // 2
            new StatementDefinition(
                    "16",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_xYXZ9k",
                    List.of(
                            new StatementTypeDefinition(
                                    "69218cc0-635f-42be-9ca1-a1372fa9a513",
                                    StatementType.LIMITING,
                                    "Cieszenie się pieniędzmi świadczy o braku pokory."
                            ),
                            new StatementTypeDefinition(
                                    "92ead0f3-9c99-47a1-8a89-99e562633620",
                                    StatementType.SUPPORTING,
                                    "Mogę cieszyć się pieniędzmi i być pokorna."
                            )
                    )
            ),
            // 3
            new StatementDefinition(
                    "24",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_rPjEGo",
                    List.of(
                            new StatementTypeDefinition(
                                    "7d2818d0-bfe7-4d9f-87d3-27dfc76df971",
                                    StatementType.LIMITING,
                                    "Pragnienie bogactwa jest złe."
                            ),
                            new StatementTypeDefinition(
                                    "e49ffe21-9ef4-41d8-8dcf-04436b47b11f",
                                    StatementType.SUPPORTING,
                                    "Mogę pragnąć bogactwa z czystymi intencjami."
                            )
                    )
            ),
            // 4
            new StatementDefinition(
                    "32",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_QVK0vY",
                    List.of(
                            new StatementTypeDefinition(
                                    "46b1d33c-ee87-4c31-9a4d-950c5cb37d17",
                                    StatementType.LIMITING,
                                    "Kiedy ludzie mają za dużo, często gubią siebie."
                            ),
                            new StatementTypeDefinition(
                                    "b4fd64e8-be45-4003-a372-092a100a5e97",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć dużo i pozostać sobą."
                            )
                    )
            ),
            // 5
            new StatementDefinition(
                    "40",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_Av78Ay",
                    List.of(
                            new StatementTypeDefinition(
                                    "0858c885-5499-4f3a-b5ab-adf39467254c",
                                    StatementType.LIMITING,
                                    "Ludzie myślą źle o tych, którzy mają dużo pieniędzy."
                            ),
                            new StatementTypeDefinition(
                                    "fe365843-dc9a-41a9-95d8-5136febc5223",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć dużo pieniędzy i być dobrym człowiekiem."
                            )
                    )
            ),
            // 6
            new StatementDefinition(
                    "48",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_MRe5vl",
                    List.of(
                            new StatementTypeDefinition(
                                    "8edfafcf-f4c9-4e38-9b14-d60945d5be9f",
                                    StatementType.LIMITING,
                                    "Tylko skromne życie jest wartościowe."
                            ),
                            new StatementTypeDefinition(
                                    "c4b617ea-e9d7-430a-ade9-804d00811342",
                                    StatementType.SUPPORTING,
                                    "Wartość życia nie zależy od tego, ile mam."
                            )
                    )
            ),
            // 7
            new StatementDefinition(
                    "56",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_5xB28N",
                    List.of(
                            new StatementTypeDefinition(
                                    "61d7be8e-a877-4dc4-bc21-d9c91b16d8b7",
                                    StatementType.LIMITING,
                                    "Bogactwo często psuje ludzi."
                            ),
                            new StatementTypeDefinition(
                                    "11c79eb6-bd95-428e-92de-042d46a5e405",
                                    StatementType.SUPPORTING,
                                    "Bogactwo może pokazywać to, co w człowieku dobre."
                            )
                    )
            ),
            // 8
            new StatementDefinition(
                    "64",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_OGD0ba",
                    List.of(
                            new StatementTypeDefinition(
                                    "976c0045-44fc-482f-bb8b-2b405d5aa640",
                                    StatementType.LIMITING,
                                    "Pieniądze dają władzę nad ludźmi."
                            ),
                            new StatementTypeDefinition(
                                    "1d85ee01-5955-4cbc-95e3-1ce3ea1f79d4",
                                    StatementType.SUPPORTING,
                                    "Pieniądze dają możliwość czynienia dobra."
                            )
                    )
            ),
            // 9
            new StatementDefinition(
                    "72",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_xYXZRd",
                    List.of(
                            new StatementTypeDefinition(
                                    "7a76d6bc-531a-4101-bb55-d38b5da98797",
                                    StatementType.LIMITING,
                                    "Prawdziwa wartość człowieka leży w prostocie i skromności."
                            ),
                            new StatementTypeDefinition(
                                    "2d92917b-aa10-4522-aa7e-edf906c7e241",
                                    StatementType.SUPPORTING,
                                    "Moja wartość nie zależy od tego, ile posiadam."
                            )
                    )
            ),
            // 10
            new StatementDefinition(
                    "80",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_adQGAZ",
                    List.of(
                            new StatementTypeDefinition(
                                    "3e48b67b-f9fc-413d-9020-92bf3436e786",
                                    StatementType.LIMITING,
                                    "Nie wypada mieć więcej niż inni."
                            ),
                            new StatementTypeDefinition(
                                    "0565e3c7-5865-49b3-9e12-77495c3339e9",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć więcej i nadal być dobrym człowiekiem."
                            )
                    )
            ),
            // 11
            new StatementDefinition(
                    "88",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_K1Y0Wg",
                    List.of(
                            new StatementTypeDefinition(
                                    "61b012b0-7c98-49b8-970f-d93188899282",
                                    StatementType.LIMITING,
                                    "Pieniądze oddzielają ludzi od siebie."
                            ),
                            new StatementTypeDefinition(
                                    "0a2e8ba4-3558-4dda-9a69-5b2bfd8279cd",
                                    StatementType.SUPPORTING,
                                    "Pieniądze mogą wspierać relacje i bliskość."
                            )
                    )
            ),
            // 12
            new StatementDefinition(
                    "96",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_X0xq8P",
                    List.of(
                            new StatementTypeDefinition(
                                    "86b6bc69-ecf0-42e1-84a8-4d6af876d716",
                                    StatementType.LIMITING,
                                    "Bycie skromną oznacza, że nie powinnam potrzebować pieniędzy."
                            ),
                            new StatementTypeDefinition(
                                    "6f8ceecd-a955-46eb-b664-738483eb937b",
                                    StatementType.SUPPORTING,
                                    "Mogę być skromna i korzystać z pieniędzy w zgodzie ze sobą."
                            )
                    )
            ),
            // 13
            new StatementDefinition(
                    "104",
                    StatementCategory.MODESTY_IDEALIST,
                    "question_lrbRYo",
                    List.of(
                            new StatementTypeDefinition(
                                    "44ce29c2-50a0-48dd-aa22-c026a798bcbd",
                                    StatementType.LIMITING,
                                    "Posiadanie dużych pieniędzy oddziela od duchowości."
                            ),
                            new StatementTypeDefinition(
                                    "1c98c85e-e7e0-4180-91ea-059f15755851",
                                    StatementType.SUPPORTING,
                                    "Pieniądze mogą wspierać moją duchowość."
                            )
                    )
            )
    );

    public static final List<StatementDefinition> BLOCKED_IN_RECEIVING = List.of(
            // 1
            new StatementDefinition(
                    "7",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_Glx0YO",
                    List.of(
                            new StatementTypeDefinition(
                                    "aeb82dc1-0732-4928-b45a-6f747d875750",
                                    StatementType.LIMITING,
                                    "Moja praca jest warta mniej niż dostaję."
                            ),
                            new StatementTypeDefinition(
                                    "cbb409cb-fcaa-4da7-8b93-22ec886b6716",
                                    StatementType.SUPPORTING,
                                    "Moja praca jest warta co najmniej tyle, ile dostaję."
                            )
                    )
            ),
            // 2 — NOWA (energia/duchowość)
            new StatementDefinition(
                    "15",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_2PErOM",
                    List.of(
                            new StatementTypeDefinition(
                                    "9da54f95-aa81-49de-88dc-370e6d2f4d78",
                                    StatementType.LIMITING,
                                    "Nie powinno się brać pieniędzy za pracę z energią/duchowością."
                            ),
                            new StatementTypeDefinition(
                                    "e3d6ba7b-0d1a-469f-bcab-97dc10de5903",
                                    StatementType.SUPPORTING,
                                    "Mogę pomagać innym na poziomie energetycznym/duchowym i jednocześnie dobrze zarabiać."
                            )
                    )
            ),
            // 3
            new StatementDefinition(
                    "23",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_EWk0vl",
                    List.of(
                            new StatementTypeDefinition(
                                    "7b0d2dd5-f55b-46b3-a099-518c66418a47",
                                    StatementType.LIMITING,
                                    "Nie zasługuję na to, żeby inni mi pomagali."
                            ),
                            new StatementTypeDefinition(
                                    "09f52d66-d552-451e-85e4-cc9d98c8153d",
                                    StatementType.SUPPORTING,
                                    "Zasługuję na to, by dostawać wsparcie i pomoc jeżeli tego potrzebuję."
                            )
                    )
            ),
            // 4
            new StatementDefinition(
                    "31",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_qV4PoO",
                    List.of(
                            new StatementTypeDefinition(
                                    "0e83fd2a-80a2-4352-963a-35fa24dea804",
                                    StatementType.LIMITING,
                                    "Trudno mi przyjąć coś bez poczucia, że powinnam się odwdzięczyć."
                            ),
                            new StatementTypeDefinition(
                                    "9f4a75ce-0e3a-4d93-b24d-bb9bc031e54a",
                                    StatementType.SUPPORTING,
                                    "Mogę przyjmować z wdzięcznością, bez obowiązku rewanżu."
                            )
                    )
            ),
            // 5
            new StatementDefinition(
                    "39",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_bd5OK6",
                    List.of(
                            new StatementTypeDefinition(
                                    "c9fa162d-afec-4df4-8865-0b09af171192",
                                    StatementType.LIMITING,
                                    "Nie wypada przyjmować pieniędzy, jeśli na nie nie zapracowałam."
                            ),
                            new StatementTypeDefinition(
                                    "135cf5f1-5112-4b5b-ac00-708eaee9a423",
                                    StatementType.SUPPORTING,
                                    "Mogę przyjmować pieniądze z wdzięcznością, nawet jeśli nie były wynikiem mojego wysiłku."
                            )
                    )
            ),
            // 6
            new StatementDefinition(
                    "47",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_1VAEDg",
                    List.of(
                            new StatementTypeDefinition(
                                    "8fc3358a-1f1f-4928-a536-929654efb058",
                                    StatementType.LIMITING,
                                    "Przyjmowanie prezentów lub pieniędzy jest krępujące."
                            ),
                            new StatementTypeDefinition(
                                    "8e6bea6c-0cb5-411d-afed-6dd0274241bb",
                                    StatementType.SUPPORTING,
                                    "Przyjmowanie prezentów i pieniędzy jest powodem do wdzięczności i radości."
                            )
                    )
            ),
            // 7
            new StatementDefinition(
                    "55",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_zYxe2k",
                    List.of(
                            new StatementTypeDefinition(
                                    "02408247-8c20-4430-9fe5-c3e2df8f6ce3",
                                    StatementType.LIMITING,
                                    "Stawiam innych ponad siebie."
                            ),
                            new StatementTypeDefinition(
                                    "d2b26d9e-d1ef-438d-9eb2-64f2da142b89",
                                    StatementType.SUPPORTING,
                                    "Troszczę się o innych, pamiętając o sobie i swoich potrzebach."
                            )
                    )
            ),
            // 8
            new StatementDefinition(
                    "63",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_Glx0qe",
                    List.of(
                            new StatementTypeDefinition(
                                    "44f58ff5-ed1d-4f36-8d2b-9a491e50fc7b",
                                    StatementType.LIMITING,
                                    "Ludzie lubią mnie tylko, gdy im coś daję."
                            ),
                            new StatementTypeDefinition(
                                    "482c633a-2bda-4797-9c55-dfb992136939",
                                    StatementType.SUPPORTING,
                                    "Jestem lubiana i kochana za to, kim jestem."
                            )
                    )
            ),
            // 9
            new StatementDefinition(
                    "71",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_2PErle",
                    List.of(
                            new StatementTypeDefinition(
                                    "1ddfb90b-c648-448c-aa7a-bf859fb96d2e",
                                    StatementType.LIMITING,
                                    "Nie wypada brać pieniędzy za coś, co przychodzi mi z łatwością."
                            ),
                            new StatementTypeDefinition(
                                    "9fb7646f-cfd1-4dc0-b4b2-93cfb3347dfb",
                                    StatementType.SUPPORTING,
                                    "Mogę zarabiać również na tym, co przychodzi mi z lekkością i radością."
                            )
                    )
            ),
            // 10
            new StatementDefinition(
                    "79",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_WzO0Ke",
                    List.of(
                            new StatementTypeDefinition(
                                    "989bdb07-9c85-4446-8514-677a690b4b77",
                                    StatementType.LIMITING,
                                    "Wolę nie mówić o pieniądzach, żeby inni nie czuli się źle."
                            ),
                            new StatementTypeDefinition(
                                    "2c4c1933-fdca-4cc8-bd22-93e81b81dcc6",
                                    StatementType.SUPPORTING,
                                    "Mogę mówić o pieniądzach i czuć się z tym dobrze."
                            )
                    )
            ),
            // 11
            new StatementDefinition(
                    "87",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_vYG2Jg",
                    List.of(
                            new StatementTypeDefinition(
                                    "2ae24e27-c60e-4d6e-91f8-1d8b87661ceb",
                                    StatementType.LIMITING,
                                    "W moim wieku lepiej zainwestować w coś konkretnego albo w dzieci, niż w siebie."
                            ),
                            new StatementTypeDefinition(
                                    "288419e0-3422-49bb-98fd-62d27044fab3",
                                    StatementType.SUPPORTING,
                                    "Zawsze warto inwestować w siebie, niezależnie od wieku i etapu życia."
                            )
                    )
            ),
            // 12
            new StatementDefinition(
                    "95",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_yYPDo8",
                    List.of(
                            new StatementTypeDefinition(
                                    "1e7ce3f0-b5ad-48bc-8edd-72eb9c82a3db",
                                    StatementType.LIMITING,
                                    "Pozwalanie sobie na luksus to przesada."
                            ),
                            new StatementTypeDefinition(
                                    "3340e450-95e0-4404-ac9c-6da6ba0df1a5",
                                    StatementType.SUPPORTING,
                                    "Mogę cieszyć się luksusem bez poczucia winy."
                            )
                    )
            ),
            // 13
            new StatementDefinition(
                    "103",
                    StatementCategory.BLOCKED_IN_RECEIVING,
                    "question_Dz4exl",
                    List.of(
                            new StatementTypeDefinition(
                                    "430577ab-58fa-4982-adde-698f98b75651",
                                    StatementType.LIMITING,
                                    "Przyjmując pomoc, mam wrażenie, że jestem coś winna."
                            ),
                            new StatementTypeDefinition(
                                    "75992b58-eced-4b08-bfb6-8836ca9d6c4c",
                                    StatementType.SUPPORTING,
                                    "Mogę przyjmować wsparcie bez poczucia, że muszę się odwdzięczyć."
                            )
                    )
            )
    );

    public static final List<StatementDefinition> OVERWORKED_PERFECTIONIST = List.of(
            // 1
            new StatementDefinition(
                    "6",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_oGbOlN",
                    List.of(
                            new StatementTypeDefinition(
                                    "452d8039-31af-4a84-bb71-297cf0fdc82f",
                                    StatementType.LIMITING,
                                    "Na pieniądze trzeba ciężko pracować i się poświęcać."
                            ),
                            new StatementTypeDefinition(
                                    "7ebcca32-d151-40f1-b755-ec5eedb6ddb3",
                                    StatementType.SUPPORTING,
                                    "Mogę zarabiać pieniądze w lekki i przyjemny sposób."
                            )
                    )
            ),
            // 2
            new StatementDefinition(
                    "14",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_jPaxG1",
                    List.of(
                            new StatementTypeDefinition(
                                    "131d49ec-595f-42ce-8730-6ff6b90d8a50",
                                    StatementType.LIMITING,
                                    "Nie wierzę, że można dobrze zarabiać bez ciężkiej pracy."
                            ),
                            new StatementTypeDefinition(
                                    "294283c7-672d-404f-af74-0d7f1f252250",
                                    StatementType.SUPPORTING,
                                    "Zasługuję na dobre pieniądze, także gdy pracuję lekko."
                            )
                    )
            ),
            // 3
            new StatementDefinition(
                    "22",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_POd0v1",
                    List.of(
                            new StatementTypeDefinition(
                                    "c6edd809-04f5-4451-a424-86e254b3ee17",
                                    StatementType.LIMITING,
                                    "Nie mogę pozwolić sobie na odpoczynek i przyjemność."
                            ),
                            new StatementTypeDefinition(
                                    "76cb9cab-70d4-443e-a43f-4868af2b18b5",
                                    StatementType.SUPPORTING,
                                    "Mogę odpoczywać i cieszyć się tym bez poczucia winy."
                            )
                    )
            ),
            // 4
            new StatementDefinition(
                    "30",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_Nop0M0",
                    List.of(
                            new StatementTypeDefinition(
                                    "da6296cb-2fd7-46a5-b535-aa1fba27d827",
                                    StatementType.LIMITING,
                                    "Zawsze muszę być produktywna, czuję się winna, gdy nie robię wystarczająco."
                            ),
                            new StatementTypeDefinition(
                                    "a163773f-f192-4210-afbc-aa1a8f5f44d5",
                                    StatementType.SUPPORTING,
                                    "Mogę odpoczywać bez poczucia winy."
                            )
                    )
            ),
            // 5
            new StatementDefinition(
                    "38",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_7xRZ1a",
                    List.of(
                            new StatementTypeDefinition(
                                    "eeb0854a-5699-41ce-8aac-964610747e46",
                                    StatementType.LIMITING,
                                    "Wypalenie jest ceną sukcesu."
                            ),
                            new StatementTypeDefinition(
                                    "e5767a6c-63d8-4810-85b1-fea4cc05e442",
                                    StatementType.SUPPORTING,
                                    "Sukces dodaje energii."
                            )
                    )
            ),
            // 6
            new StatementDefinition(
                    "46",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_pObVR1",
                    List.of(
                            new StatementTypeDefinition(
                                    "da03f62a-81b0-49d3-8972-e186d0b086bd",
                                    StatementType.LIMITING,
                                    "Zarabianie to stres."
                            ),
                            new StatementTypeDefinition(
                                    "d35d7a57-4cac-491f-8276-27f663eb7279",
                                    StatementType.SUPPORTING,
                                    "Zarabianie jest przyjemne i daje mi satysfakcję."
                            )
                    )
            ),
            // 7
            new StatementDefinition(
                    "54",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_0OQP19",
                    List.of(
                            new StatementTypeDefinition(
                                    "0792c2f8-bef2-431e-a9c7-8ca297222a5a",
                                    StatementType.LIMITING,
                                    "Trzeba mieć „fach w ręku”, żeby zarabiać."
                            ),
                            new StatementTypeDefinition(
                                    "1e70fd76-0cf4-4b34-9e8b-b89252f2bb8f",
                                    StatementType.SUPPORTING,
                                    "Mogę zarabiać dzięki swoim talentom i pomysłom."
                            )
                    )
            ),
            // 8
            new StatementDefinition(
                    "62",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_oGbO5b",
                    List.of(
                            new StatementTypeDefinition(
                                    "08cea63d-ee89-423c-8790-d3c7c04fdb5c",
                                    StatementType.LIMITING,
                                    "Muszę wyglądać, jakbym wszystko miała pod kontrolą."
                            ),
                            new StatementTypeDefinition(
                                    "a22ab743-3e31-4866-a2e4-d3fcedf55201",
                                    StatementType.SUPPORTING,
                                    "Nie muszę być idealna, żeby zasługiwać na szacunek."
                            )
                    )
            ),
            // 9
            new StatementDefinition(
                    "70",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_jPaxJQ",
                    List.of(
                            new StatementTypeDefinition(
                                    "97f20b00-ae43-4bb3-96aa-8a41cd1a121b",
                                    StatementType.LIMITING,
                                    "Pracuję ciężko, a wciąż za mało z tego mam."
                            ),
                            new StatementTypeDefinition(
                                    "94b802ff-1b9b-4b21-b3d1-40f20baa91c3",
                                    StatementType.SUPPORTING,
                                    "Moja praca przynosi mi adekwatne zyski."
                            )
                    )
            ),
            // 10
            new StatementDefinition(
                    "78",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_eRqE0x",
                    List.of(
                            new StatementTypeDefinition(
                                    "0e63135b-e833-4ace-9f17-314920ed6ea0",
                                    StatementType.LIMITING,
                                    "Na przyjemność trzeba zasłużyć."
                            ),
                            new StatementTypeDefinition(
                                    "db220017-584d-480f-a475-60ef7809470f",
                                    StatementType.SUPPORTING,
                                    "Mogę cieszyć się przyjemnością bez poczucia winy."
                            )
                    )
            ),
            // 11
            new StatementDefinition(
                    "86",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_kEdZJr",
                    List.of(
                            new StatementTypeDefinition(
                                    "a5f2a5aa-1bd9-46d2-9d0c-b346c76a662b",
                                    StatementType.LIMITING,
                                    "Jeśli ja tego nie udźwignę, wszystko się zawali."
                            ),
                            new StatementTypeDefinition(
                                    "cfc2ea40-165e-4b50-9774-088f4a845211",
                                    StatementType.SUPPORTING,
                                    "Świat może działać dobrze, nawet gdy ja odpoczywam."
                            )
                    )
            ),
            // 12
            new StatementDefinition(
                    "94",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_gGD4JP",
                    List.of(
                            new StatementTypeDefinition(
                                    "abbee06c-ec4a-47b6-a8b7-e6f0a9ea9551",
                                    StatementType.LIMITING,
                                    "Jeśli zrobię błąd, wszystko stracę."
                            ),
                            new StatementTypeDefinition(
                                    "c873016b-dc85-431e-bec1-951818c559e4",
                                    StatementType.SUPPORTING,
                                    "Błędy są częścią nauki, mogę dalej się rozwijać."
                            )
                    )
            ),
            // 13
            new StatementDefinition(
                    "102",
                    StatementCategory.OVERWORKED_PERFECTIONIST,
                    "question_Ya5dJN",
                    List.of(
                            new StatementTypeDefinition(
                                    "fbe0faec-1392-41ac-af02-d8f1161b7d7b",
                                    StatementType.LIMITING,
                                    "Świętowanie sukcesów to strata czasu."
                            ),
                            new StatementTypeDefinition(
                                    "48bfdabf-e674-4eb6-8ff8-1659182df8d3",
                                    StatementType.SUPPORTING,
                                    "Świętowanie sukcesów daje siłę i motywację."
                            )
                    )
            )
    );

    public static final List<StatementDefinition> WITHDRAWN_LEADER = List.of(
            // 1
            new StatementDefinition(
                    "5",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_RPGL8Q",
                    List.of(
                            new StatementTypeDefinition(
                                    "3b3da254-3ff7-43da-983c-d1f8368d934a",
                                    StatementType.LIMITING,
                                    "Ludzie mogą mnie skrzywdzić, gdy zobaczą, że mam pieniądze."
                            ),
                            new StatementTypeDefinition(
                                    "6166195e-e5d5-4246-b134-68855bdb0ed4",
                                    StatementType.SUPPORTING,
                                    "Mogę być bogata i czuć się bezpiecznie."
                            )
                    )
            ),
            // 2
            new StatementDefinition(
                    "13",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_4r4jP5",
                    List.of(
                            new StatementTypeDefinition(
                                    "06519f92-50ec-4cdc-b224-6c0927785582",
                                    StatementType.LIMITING,
                                    "Mój sukces finansowy wywołuje zazdrość innych."
                            ),
                            new StatementTypeDefinition(
                                    "b7774a25-4ffc-4fe1-937d-e46726f44a49",
                                    StatementType.SUPPORTING,
                                    "Sukces finansowy może budzić inspirację i szacunek."
                            )
                    )
            ),
            // 3
            new StatementDefinition(
                    "21",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_VJL8vj",
                    List.of(
                            new StatementTypeDefinition(
                                    "f104e311-3584-4659-b441-60063919aff5",
                                    StatementType.LIMITING,
                                    "Boję się, że ludzie uznają mnie za chciwą, jeśli będę bogata."
                            ),
                            new StatementTypeDefinition(
                                    "f57d451e-758a-40bd-9c74-450c6c96969b",
                                    StatementType.SUPPORTING,
                                    "Mogę być bogata i postrzegana z szacunkiem."
                            )
                    )
            ),
            // 4
            new StatementDefinition(
                    "29",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_ZajJvV",
                    List.of(
                            new StatementTypeDefinition(
                                    "0df68696-d5a3-4d08-ae6e-e0bf3cf2cb90",
                                    StatementType.LIMITING,
                                    "Kiedy mam mniej, czuję, że inni będą mnie oceniać."
                            ),
                            new StatementTypeDefinition(
                                    "32940acd-4c32-45b9-b4a1-8f5e554222ee",
                                    StatementType.SUPPORTING,
                                    "Mogę mówić o pieniądzach bez wstydu, nawet jak mam chwilowe trudności."
                            )
                    )
            ),
            // 5 — NOWA z Tally: „Kiedy okazuję zaufanie…”
            new StatementDefinition(
                    "37",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_6N8xAA",
                    List.of(
                            new StatementTypeDefinition(
                                    "cc606d53-3a97-4307-8ca5-8f3d85414356",
                                    StatementType.LIMITING,
                                    "Kiedy okazuję zaufanie, ludzie to wykorzystują."
                            ),
                            new StatementTypeDefinition(
                                    "83660518-e42a-4369-9cb9-d1fe7623ec4c",
                                    StatementType.SUPPORTING,
                                    "Mogę ufać i zachować swoje granice."
                            )
                    )
            ),
            // 6
            new StatementDefinition(
                    "45",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_LpZ0vy",
                    List.of(
                            new StatementTypeDefinition(
                                    "23d62f38-2613-476e-8fa0-ac848abf7139",
                                    StatementType.LIMITING,
                                    "Im więcej mam, tym bardziej jestem narażona na ataki."
                            ),
                            new StatementTypeDefinition(
                                    "4a827955-550b-4114-98ab-09ceffef65ab",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć dużo i czuć się chroniona."
                            )
                    )
            ),
            // 7
            new StatementDefinition(
                    "53",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_8x1eAl",
                    List.of(
                            new StatementTypeDefinition(
                                    "bccb0c82-11d8-43cb-99d2-8c7ef7542fc4",
                                    StatementType.LIMITING,
                                    "Sukces finansowy to samotność."
                            ),
                            new StatementTypeDefinition(
                                    "6410c7b5-7f40-480d-ac78-2e7196f01492",
                                    StatementType.SUPPORTING,
                                    "Mogę cieszyć się sukcesem finansowym, otoczona ludźmi, których lubię."
                            )
                    )
            ),
            // 8
            new StatementDefinition(
                    "61",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_RPGLbK",
                    List.of(
                            new StatementTypeDefinition(
                                    "c97261e2-3c78-4954-a439-5c5c3c9dd390",
                                    StatementType.LIMITING,
                                    "Pokazanie, że mam więcej, sprowokuje ataki."
                            ),
                            new StatementTypeDefinition(
                                    "cc745cf3-700c-4daf-91b3-f7483aca2f5d",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć więcej i czuć się bezpieczna."
                            )
                    )
            ),
            // 9
            new StatementDefinition(
                    "69",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_4r4j9r",
                    List.of(
                            new StatementTypeDefinition(
                                    "2e3fc0f2-ad35-4337-8d83-7e0a24fddaa2",
                                    StatementType.LIMITING,
                                    "Jeśli będę bogata, ludzie, których znam, się ode mnie odsuną."
                            ),
                            new StatementTypeDefinition(
                                    "ec5b5379-bcd9-48cf-a367-e12df847eb6d",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć więcej i nadal być blisko z ludźmi, którzy są dla mnie ważni."
                            )
                    )
            ),
            // 10
            new StatementDefinition(
                    "77",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_9QqlRE",
                    List.of(
                            new StatementTypeDefinition(
                                    "35f4afd1-a215-4f64-8885-6f21ca408879",
                                    StatementType.LIMITING,
                                    "Lepiej mieć mniej, niż mierzyć się z tym, co niesie bogactwo."
                            ),
                            new StatementTypeDefinition(
                                    "6003507f-10d0-4029-8a93-552906f8f798",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć duże pieniądze i czuć się z tym dobrze i bezpiecznie."
                            )
                    )
            ),
            // 11
            new StatementDefinition(
                    "85",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_B7zBje",
                    List.of(
                            new StatementTypeDefinition(
                                    "bfb999ef-2eb1-4f94-92db-64da9eb64d08",
                                    StatementType.LIMITING,
                                    "Lepiej się nie wychylać, wtedy będę bezpieczna."
                            ),
                            new StatementTypeDefinition(
                                    "1d33d1ef-7e99-4844-8a68-2e0fddb2034e",
                                    StatementType.SUPPORTING,
                                    "Mogę być widoczna i nadal czuć się bezpiecznie."
                            )
                    )
            ),
            // 12
            new StatementDefinition(
                    "93",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_Jz90er",
                    List.of(
                            new StatementTypeDefinition(
                                    "4113e615-98c1-4b39-a627-1a11804495dd",
                                    StatementType.LIMITING,
                                    "Sukces odbierze mi prywatność."
                            ),
                            new StatementTypeDefinition(
                                    "7c40c373-36a3-4b5b-99a4-bb163bfd52ef",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć sukces i zachować prywatność, której potrzebuję."
                            )
                    )
            ),
            // 13
            new StatementDefinition(
                    "101",
                    StatementCategory.WITHDRAWN_LEADER,
                    "question_ddWP8r",
                    List.of(
                            new StatementTypeDefinition(
                                    "a356741d-7c1e-4864-8a32-7da077997cb2",
                                    StatementType.LIMITING,
                                    "Jeśli pokażę, że odniosłam sukces, ludzie będą zazdrośni."
                            ),
                            new StatementTypeDefinition(
                                    "6196d10f-8594-49da-98e4-f5f230db20ba",
                                    StatementType.SUPPORTING,
                                    "Mogę dzielić się swoim sukcesem i przyciągać życzliwość."
                            )
                    )
            )
    );

    public static final List<StatementDefinition> LOYAL_HEIRESS = List.of(
            // 1
            new StatementDefinition(
                    "4",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_lrbRVX",
                    List.of(
                            new StatementTypeDefinition(
                                    "c74946df-3b8c-413a-94d2-2cd991379006",
                                    StatementType.LIMITING,
                                    "Czuję, że nie mogę mieć więcej niż moi bliscy."
                            ),
                            new StatementTypeDefinition(
                                    "f39621fd-c4f5-4aa4-9d32-5b7e81e0f53e",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć więcej i nadal czuć więź z bliskimi osobami."
                            )
                    )
            ),
            // 2
            new StatementDefinition(
                    "12",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_rPjE2X",
                    List.of(
                            new StatementTypeDefinition(
                                    "a7f0a1d5-e698-4503-8a4c-2a3f64f5c055",
                                    StatementType.LIMITING,
                                    "Jeśli odniosę sukces, rodzice mogą odebrać to jako zagrożenie."
                            ),
                            new StatementTypeDefinition(
                                    "a033654e-52dc-4a62-8cbc-5674818f129a",
                                    StatementType.SUPPORTING,
                                    "Mój sukces niczego rodzinie nie odbiera, mogę go celebrować."
                            )
                    )
            ),
            // 3
            new StatementDefinition(
                    "20",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_OGD0da",
                    List.of(
                            new StatementTypeDefinition(
                                    "93af7aa5-52b8-4ff7-955c-ff2e0fd8ff81",
                                    StatementType.LIMITING,
                                    "Jeśli się wzbogacę, stracę rodzinę lub przyjaciół."
                            ),
                            new StatementTypeDefinition(
                                    "ed9c6969-a8e4-4e94-929d-9c8554bd2b75",
                                    StatementType.SUPPORTING,
                                    "Mogę się wzbogacić i nadal utrzymywać bliskie relacje."
                            )
                    )
            ),
            // 4
            new StatementDefinition(
                    "28",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_xYXZ4d",
                    List.of(
                            new StatementTypeDefinition(
                                    "307d136c-77b3-41ce-828c-db29efb809f4",
                                    StatementType.LIMITING,
                                    "Nie mogę mieć lepiej niż moi rodzice."
                            ),
                            new StatementTypeDefinition(
                                    "5d11f622-a9ab-4e5d-95d2-1bf916f96e34",
                                    StatementType.SUPPORTING,
                                    "Mogę tworzyć nowe wzorce finansowe w mojej rodzinie."
                            )
                    )
            ),
            // 5
            new StatementDefinition(
                    "36",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_adQGKZ",
                    List.of(
                            new StatementTypeDefinition(
                                    "79d79e9d-0913-464b-a909-e559c4cb7bd2",
                                    StatementType.LIMITING,
                                    "Czuję, że muszę dźwigać finansowe ciężary mojej rodziny."
                            ),
                            new StatementTypeDefinition(
                                    "d6436d49-794a-4396-aea2-a3de96cdade2",
                                    StatementType.SUPPORTING,
                                    "Mogę oddać to, co nie jest moje, i zająć się własnym życiem."
                            )
                    )
            ),
            // 6 — „Kiedy jestem wśród ludzi…”
            new StatementDefinition(
                    "44",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_K1Y0vg",
                    List.of(
                            new StatementTypeDefinition(
                                    "bbd03726-0b71-4764-974e-d4d04a376ec7",
                                    StatementType.LIMITING,
                                    "Kiedy jestem wśród ludzi, którzy mają mniej ode mnie, czuję się z tym źle."
                            ),
                            new StatementTypeDefinition(
                                    "e4188b4c-ab78-4142-871c-77247518853c",
                                    StatementType.SUPPORTING,
                                    "Mogę czuć się dobrze z tym, co mam, niezależnie od sytuacji innych."
                            )
                    )
            ),
            // 7 — „Bieda uszlachetnia.”
            new StatementDefinition(
                    "52",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_X0xq1P",
                    List.of(
                            new StatementTypeDefinition(
                                    "cc35c16d-0c9d-4b96-b560-161bd680d42f",
                                    StatementType.LIMITING,
                                    "Bieda uszlachetnia."
                            ),
                            new StatementTypeDefinition(
                                    "285e0982-3ee4-4cf4-9bf6-29b49bfb24ca",
                                    StatementType.SUPPORTING,
                                    "Mogę być szlachetna i bogata jednocześnie."
                            )
                    )
            ),
            // 8
            new StatementDefinition(
                    "60",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_lrbRKo",
                    List.of(
                            new StatementTypeDefinition(
                                    "f2054a4b-207e-4028-b336-437dfa5eb33a",
                                    StatementType.LIMITING,
                                    "Boję się, że kiedy mam więcej, ludzie kochają mnie nie za to, kim jestem, tylko za to, co mam."
                            ),
                            new StatementTypeDefinition(
                                    "ffb1572d-aae3-4203-bc51-956111360466",
                                    StatementType.SUPPORTING,
                                    "Mogę być kochana za to, kim jestem, niezależnie od tego, ile mam."
                            )
                    )
            ),
            // 9
            new StatementDefinition(
                    "68",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_rPjEJo",
                    List.of(
                            new StatementTypeDefinition(
                                    "8f9f6888-4ec9-4189-938e-3046ede0389a",
                                    StatementType.LIMITING,
                                    "Nie wypada chcieć zbyt wiele."
                            ),
                            new StatementTypeDefinition(
                                    "02cd15c8-84dc-47ee-81bc-2c87ef8b35dd",
                                    StatementType.SUPPORTING,
                                    "Mogę chcieć więcej, nie tracąc wdzięczności za to, co mam."
                            )
                    )
            ),
            // 10
            new StatementDefinition(
                    "76",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_QVK0zY",
                    List.of(
                            new StatementTypeDefinition(
                                    "529efa52-84f4-4856-976a-1f9891af8403",
                                    StatementType.LIMITING,
                                    "Lepiej pozostać skromną niż być ocenianą za sukces."
                            ),
                            new StatementTypeDefinition(
                                    "2240d9ad-f8ba-488f-9c36-ae5a7074def2",
                                    StatementType.SUPPORTING,
                                    "Widoczność mojego sukcesu nie musi oznaczać oceny czy odrzucenia."
                            )
                    )
            ),
            // 11
            new StatementDefinition(
                    "84",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_Av784y",
                    List.of(
                            new StatementTypeDefinition(
                                    "58f94ae6-58f1-4c40-9f1d-97254cd7a36c",
                                    StatementType.LIMITING,
                                    "Jak się wzbogacę, zostanę sama."
                            ),
                            new StatementTypeDefinition(
                                    "9b4cdda5-f462-49fc-8070-5e39de7c071e",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć więcej i nadal mieć bliskie relacje z innymi."
                            )
                    )
            ),
            // 12
            new StatementDefinition(
                    "92",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_MRe56l",
                    List.of(
                            new StatementTypeDefinition(
                                    "cabb9110-0df6-474a-9053-9e1efafb6402",
                                    StatementType.LIMITING,
                                    "W mojej rodzinie wszyscy odnieśli sukces finansowy, ja też muszę."
                            ),
                            new StatementTypeDefinition(
                                    "14cd821a-99e7-4cfa-98d8-bb2c05670131",
                                    StatementType.SUPPORTING,
                                    "Mogę podążać własną drogą i definiować sukces po swojemu."
                            )
                    )
            ),
            // 13
            new StatementDefinition(
                    "100",
                    StatementCategory.LOYAL_HEIRESS,
                    "question_5xB2eN",
                    List.of(
                            new StatementTypeDefinition(
                                    "a6559190-b545-4470-b7f3-fd73ce6deae9",
                                    StatementType.LIMITING,
                                    "Nie mogę mieć tego, czego moja mama/babcia nie miała."
                            ),
                            new StatementTypeDefinition(
                                    "0c9ab601-4869-49e3-bb9b-c5c6434da5d7",
                                    StatementType.SUPPORTING,
                                    "Mogę tworzyć nowe, lepsze wzorce finansowe dla mojego pokolenia."
                            )
                    )
            )
    );

    public static final List<StatementDefinition> FROZEN_VISIONARY = List.of(
            // 1
            new StatementDefinition(
                    "3",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_Dz4e0E",
                    List.of(
                            new StatementTypeDefinition(
                                    "4aa17356-ce73-4e90-baa2-53ff40b2514a",
                                    StatementType.LIMITING,
                                    "Zajmuję się finansami dopiero, gdy zbliża się kryzys."
                            ),
                            new StatementTypeDefinition(
                                    "8f4c23ba-e48a-4d32-bca3-bf3ebb9dbcbc",
                                    StatementType.SUPPORTING,
                                    "Robię plany finansowe na spokojnie, zanim nadejdzie kryzys."
                            )
                    )
            ),
            // 2
            new StatementDefinition(
                    "11",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_EWk082",
                    List.of(
                            new StatementTypeDefinition(
                                    "922ef7f6-cf01-4e7e-b601-a8fcc8fd34fb",
                                    StatementType.LIMITING,
                                    "„Zamrażam” pieniądze i pomysły, bo boję się błędu."
                            ),
                            new StatementTypeDefinition(
                                    "18cba0ea-c898-48a3-a724-9c7b151e8501",
                                    StatementType.SUPPORTING,
                                    "Mogę działać, nawet jeśli nie będzie idealnie."
                            )
                    )
            ),
            // 3
            new StatementDefinition(
                    "19",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_Glx0Xe",
                    List.of(
                            new StatementTypeDefinition(
                                    "394d5348-12c9-4ce4-9c87-c8f298d21943",
                                    StatementType.LIMITING,
                                    "Nie ma sensu działać, skoro nie wiem, czego chcę."
                            ),
                            new StatementTypeDefinition(
                                    "615874f4-933e-45fb-89e7-e9ae97c77d51",
                                    StatementType.SUPPORTING,
                                    "Mogę ruszyć i odkrywać po drodze, czego chcę."
                            )
                    )
            ),
            // 4 — (z Tally) „Sukces kojarzy mi się z presją…”
            new StatementDefinition(
                    "27",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_2PEr1e",
                    List.of(
                            new StatementTypeDefinition(
                                    "e7f4f70c-80be-4f28-a5f0-c241f312d221",
                                    StatementType.LIMITING,
                                    "Sukces kojarzy mi się z presją i stresem."
                            ),
                            new StatementTypeDefinition(
                                    "61baf9a9-7cc8-4ac0-b5b1-6788233e7f4f",
                                    StatementType.SUPPORTING,
                                    "Mogę odnieść sukces i nadal czuć spokój."
                            )
                    )
            ),
            // 5 — (z Tally) „Nie mogę wyjść poza swoją pozycję…”
            new StatementDefinition(
                    "35",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_WzO0De",
                    List.of(
                            new StatementTypeDefinition(
                                    "28f8c411-8873-40a2-92d0-d5cb57604967",
                                    StatementType.LIMITING,
                                    "Nie mogę wyjść poza swoją pozycję, boję się zrobić krok, bo mogę coś stracić."
                            ),
                            new StatementTypeDefinition(
                                    "b09f116d-58b5-4048-84d0-9a7ec2e0af01",
                                    StatementType.SUPPORTING,
                                    "Mogę wyjść poza swoją pozycję w życiu i czuć się bezpiecznie w zmianie."
                            )
                    )
            ),
            // 6 — (z Tally) „Nie jestem wystarczająco dobra…”
            new StatementDefinition(
                    "43",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_vYG2Eg",
                    List.of(
                            new StatementTypeDefinition(
                                    "5a0ee61e-e624-4870-b759-bb5bc9d43800",
                                    StatementType.LIMITING,
                                    "Nie jestem wystarczająco dobra, żeby zarabiać dużo."
                            ),
                            new StatementTypeDefinition(
                                    "91102d9a-8633-40f7-be60-9fbba3dd6e33",
                                    StatementType.SUPPORTING,
                                    "Jestem wystarczająco dobra, by zarabiać dobrze."
                            )
                    )
            ),
            // 7
            new StatementDefinition(
                    "51",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_yYPDj8",
                    List.of(
                            new StatementTypeDefinition(
                                    "983180e6-b9d7-48dd-b777-8daa91710be5",
                                    StatementType.LIMITING,
                                    "Nie ruszę, dopóki nie będę perfekcyjnie przygotowana."
                            ),
                            new StatementTypeDefinition(
                                    "54051916-6b13-4cc6-a707-3ad89ceeaffc",
                                    StatementType.SUPPORTING,
                                    "Mogę zacząć teraz i uczyć się w trakcie."
                            )
                    )
            ),
            // 8
            new StatementDefinition(
                    "59",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_Dz4eAl",
                    List.of(
                            new StatementTypeDefinition(
                                    "6f63d557-1297-440b-8ee6-8ddd3c0c8049",
                                    StatementType.LIMITING,
                                    "Nie ufam swoim decyzjom finansowym i wolę, żeby inni doradzali mi, co robić."
                            ),
                            new StatementTypeDefinition(
                                    "e675cc13-074d-404a-99e8-92a790ed785d",
                                    StatementType.SUPPORTING,
                                    "Mogę ufać sobie w decyzjach finansowych i korzystać z opinii innych."
                            )
                    )
            ),
            // 9
            new StatementDefinition(
                    "67",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_EWk0Zl",
                    List.of(
                            new StatementTypeDefinition(
                                    "2d362045-bbe4-4216-89a6-c173c6a4062e",
                                    StatementType.LIMITING,
                                    "Lepiej nie zaczynać niż ponieść porażkę."
                            ),
                            new StatementTypeDefinition(
                                    "16968212-9246-4b45-a899-bd1689ba4f5e",
                                    StatementType.SUPPORTING,
                                    "Każdy krok daje mi więcej jasności niż bezruch."
                            )
                    )
            ),
            // 10
            new StatementDefinition(
                    "75",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_qV4PJO",
                    List.of(
                            new StatementTypeDefinition(
                                    "e3417f7c-afcc-4c4b-9e8a-1b910420c9d7",
                                    StatementType.LIMITING,
                                    "Strata pieniędzy byłaby dla mnie klęską."
                            ),
                            new StatementTypeDefinition(
                                    "28eeb613-fb8d-4c07-89d4-dab610a14e8f",
                                    StatementType.SUPPORTING,
                                    "Strata pieniędzy to lekcja, z której mogę wyjść zwycięsko."
                            )
                    )
            ),
            // 11
            new StatementDefinition(
                    "83",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_bd5OY6",
                    List.of(
                            new StatementTypeDefinition(
                                    "8f4512c6-b969-4871-b2bc-29415d5f827f",
                                    StatementType.LIMITING,
                                    "Lepiej nic nie robić, niż zrobić źle."
                            ),
                            new StatementTypeDefinition(
                                    "5d77106d-36c7-42be-b5c1-edfb1f3d9037",
                                    StatementType.SUPPORTING,
                                    "Mogę zacząć działać, nawet jeśli nie wszystko jest idealne."
                            )
                    )
            ),
            // 12  — UWAGA: bez „mi”, zgodnie z Twoją decyzją
            new StatementDefinition(
                    "91",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_1VAENg",
                    List.of(
                            new StatementTypeDefinition(
                                    "8f2569d3-568a-4df4-b183-1b080281f642",
                                    StatementType.LIMITING,
                                    "Popełnianie błędów w zarządzaniu swoimi finansami to porażka."
                            ),
                            new StatementTypeDefinition(
                                    "63655d82-2bb2-4461-a0c7-7c45e7f47f24",
                                    StatementType.SUPPORTING,
                                    "Błędy są częścią uczenia się i pomagają lepiej zarządzać finansami."
                            )
                    )
            ),
            // 13
            new StatementDefinition(
                    "99",
                    StatementCategory.FROZEN_VISIONARY,
                    "question_zYxeGk",
                    List.of(
                            new StatementTypeDefinition(
                                    "9fa9c143-7850-44ec-acaa-9f4141240e20",
                                    StatementType.LIMITING,
                                    "Nie potrafię dobrze zarządzać finansami, ciągle popełniam błędy."
                            ),
                            new StatementTypeDefinition(
                                    "c6aac986-afd3-469a-b5cf-a56717964478",
                                    StatementType.SUPPORTING,
                                    "Mogę oswajać finanse krok po kroku i coraz pewniej się w nich poruszać."
                            )
                    )
            )
    );

    public static final List<StatementDefinition> SELF_SUFFICIENT_SHIELD = List.of(
            // 1
            new StatementDefinition(
                    "2",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_Ya5dVv",
                    List.of(
                            new StatementTypeDefinition(
                                    "e0a944b2-260b-447d-bc9f-6e44684e5731",
                                    StatementType.LIMITING,
                                    "Nie potrzebuję nikogo, sama wszystko ogarnę."
                            ),
                            new StatementTypeDefinition(
                                    "1c8c697b-bdbd-4652-af33-02fed78994dc",
                                    StatementType.SUPPORTING,
                                    "Mogę przyjmować wsparcie i nadal być niezależna."
                            )
                    )
            ),
            // 2 — (z Tally) „Trudno mi przyjąć pomoc…”
            new StatementDefinition(
                    "10",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_POd08x",
                    List.of(
                            new StatementTypeDefinition(
                                    "05715335-81f9-4185-94d8-46724c48ffd9",
                                    StatementType.LIMITING,
                                    "Trudno mi przyjąć pomoc bez poczucia, że muszę coś dać w zamian."
                            ),
                            new StatementTypeDefinition(
                                    "2fd2472f-8937-4c59-a188-de0f2a1887de",
                                    StatementType.SUPPORTING,
                                    "Mogę przyjmować pomoc bez poczucia winy."
                            )
                    )
            ),
            // 3 — (z Tally) „Cenię dawanie…”
            new StatementDefinition(
                    "18",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_oGbOKb",
                    List.of(
                            new StatementTypeDefinition(
                                    "593cde81-21e2-4108-8eab-7f30ed5a6b5b",
                                    StatementType.LIMITING,
                                    "Cenię dawanie, ale nie umiem brać."
                            ),
                            new StatementTypeDefinition(
                                    "aa80474b-1f18-48f7-b6ed-1ec30a0a4f47",
                                    StatementType.SUPPORTING,
                                    "Potrafię brać i dawać, czuję w tym równowagę."
                            )
                    )
            ),
            // 4 — (z Tally) „Nie mogę podnieść cen…”
            new StatementDefinition(
                    "26",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_jPax5Q",
                    List.of(
                            new StatementTypeDefinition(
                                    "4c2ec370-2abf-4fb3-849d-1c034ec775cb",
                                    StatementType.LIMITING,
                                    "Nie mogę podnieść cen, bo stracę klientów."
                            ),
                            new StatementTypeDefinition(
                                    "5da519b1-0602-48f2-9950-6b58daa627a9",
                                    StatementType.SUPPORTING,
                                    "Mogę podnieść ceny i nadal mieć klientów."
                            )
                    )
            ),
            // 5
            new StatementDefinition(
                    "34",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_eRqEKx",
                    List.of(
                            new StatementTypeDefinition(
                                    "de68cf26-5fd4-4138-ba36-e3f92a5a94e5",
                                    StatementType.LIMITING,
                                    "Muszę wszystko zrobić sama, żeby było dobrze."
                            ),
                            new StatementTypeDefinition(
                                    "7b1b3e9e-bc00-4536-8a30-16d2208789c5",
                                    StatementType.SUPPORTING,
                                    "Potrafię ufać innym i dzielić się odpowiedzialnością."
                            )
                    )
            ),
            // 6
            new StatementDefinition(
                    "42",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_kEdZKr",
                    List.of(
                            new StatementTypeDefinition(
                                    "5f47a0bb-5cf7-4afb-8ba3-85dc50d1f395",
                                    StatementType.LIMITING,
                                    "Jeśli ktoś mi pomaga, staję się od niego zależna."
                            ),
                            new StatementTypeDefinition(
                                    "730f5799-d30f-4ede-a224-39e833769acd",
                                    StatementType.SUPPORTING,
                                    "Mogę przyjmować pomoc i zachować niezależność."
                            )
                    )
            ),
            // 7
            new StatementDefinition(
                    "50",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_gGD4YP",
                    List.of(
                            new StatementTypeDefinition(
                                    "9c02d446-284d-4716-98da-921a1d6ba251",
                                    StatementType.LIMITING,
                                    "Prosząc o pomoc, pokazuję słabość."
                            ),
                            new StatementTypeDefinition(
                                    "34b73f5c-aaac-4bfe-a1a6-261a83656105",
                                    StatementType.SUPPORTING,
                                    "Prosząc o pomoc, pokazuję odwagę."
                            )
                    )
            ),
            // 8
            new StatementDefinition(
                    "58",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_Ya5dpN",
                    List.of(
                            new StatementTypeDefinition(
                                    "f41579c2-a10f-4d58-b247-7a08ac955d40",
                                    StatementType.LIMITING,
                                    "Lepiej być niezależną niż ryzykować odrzucenie."
                            ),
                            new StatementTypeDefinition(
                                    "04c5e577-1451-4abf-986c-7e6102e7ce5b",
                                    StatementType.SUPPORTING,
                                    "Mogę być niezależna i otwarta na bliskość."
                            )
                    )
            ),
            // 9
            new StatementDefinition(
                    "66",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_POd0b1",
                    List.of(
                            new StatementTypeDefinition(
                                    "e5053a3f-5496-4858-a27d-68bd96cf011f",
                                    StatementType.LIMITING,
                                    "Łatwiej mi dawać niż przyjmować."
                            ),
                            new StatementTypeDefinition(
                                    "d6cebec2-2f0d-4bde-9fb7-bcd094e65057",
                                    StatementType.SUPPORTING,
                                    "Potrafię przyjmować z taką samą otwartością, z jaką daję."
                            )
                    )
            ),
            // 10
            new StatementDefinition(
                    "74",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_Nop0b0",
                    List.of(
                            new StatementTypeDefinition(
                                    "a24d9d63-04ae-405b-a87d-f4bf3f2e7576",
                                    StatementType.LIMITING,
                                    "Przyjęcie wsparcia finansowego to utrata kontroli."
                            ),
                            new StatementTypeDefinition(
                                    "ddacfb4d-4cfa-4ae9-b89f-a4d6a06701c6",
                                    StatementType.SUPPORTING,
                                    "Mogę przyjąć wsparcie finansowe i nadal mieć kontrolę nad swoim życiem."
                            )
                    )
            ),
            // 11
            new StatementDefinition(
                    "82",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_7xRZaa",
                    List.of(
                            new StatementTypeDefinition(
                                    "a8eb51ac-cb58-4c47-80ad-c9f8d6dedc25",
                                    StatementType.LIMITING,
                                    "Muszę wszystko zabezpieczyć sama, bo nikomu nie mogę zaufać."
                            ),
                            new StatementTypeDefinition(
                                    "755cd2bc-890c-4e8a-b2b3-27a2b3e835c5",
                                    StatementType.SUPPORTING,
                                    "Mogę liczyć na innych i nadal czuć się bezpieczna."
                            )
                    )
            ),
            // 12
            new StatementDefinition(
                    "90",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_pObVJ1",
                    List.of(
                            new StatementTypeDefinition(
                                    "f9bcf501-da5d-42a2-a26a-0f7782a03b53",
                                    StatementType.LIMITING,
                                    "Pytanie o pieniądze to oznaka słabości."
                            ),
                            new StatementTypeDefinition(
                                    "ce30d776-a928-40ef-ab24-ea4ce1075537",
                                    StatementType.SUPPORTING,
                                    "Mogę pytać o pieniądze i nadal czuć się silna."
                            )
                    )
            ),
            // 13
            new StatementDefinition(
                    "98",
                    StatementCategory.SELF_SUFFICIENT_SHIELD,
                    "question_0OQPZ9",
                    List.of(
                            new StatementTypeDefinition(
                                    "0caaff45-4846-4e7e-9cf3-079e7085d1e7",
                                    StatementType.LIMITING,
                                    "Jeśli poproszę o pomoc, będę musiała się odwdzięczyć."
                            ),
                            new StatementTypeDefinition(
                                    "e663e3d9-53db-48be-83ca-491cebbbd66b",
                                    StatementType.SUPPORTING,
                                    "Mogę przyjmować pomoc bez poczucia długu."
                            )
                    )
            )
    );

    public static final List<StatementDefinition> SCARCITY_GUARDIAN = List.of(
            // 1
            new StatementDefinition(
                    "1",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_ddWPQV",
                    List.of(
                            new StatementTypeDefinition(
                                    "44c262c5-c67f-476e-a053-7f8a5976c394",
                                    StatementType.LIMITING,
                                    "Strata boli bardziej niż brak."
                            ),
                            new StatementTypeDefinition(
                                    "ac910e71-884d-427f-a86c-c036c5d904fa",
                                    StatementType.SUPPORTING,
                                    "Mogę mieć i nie bać się straty."
                            )
                    )
            ),
            // 2 — (z Tally) „Na dobre życie…”
            new StatementDefinition(
                    "9", // świadomie używam numeru z etykiety Tally; podmień jeśli masz inną konwencję
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_VJL8o6",
                    List.of(
                            new StatementTypeDefinition(
                                    "98ec1b4f-5fa5-4742-bc99-fe1e1aca035f",
                                    StatementType.LIMITING,
                                    "Na dobre życie trzeba zapracować."
                            ),
                            new StatementTypeDefinition(
                                    "1fb55f20-711f-4281-8fb8-dda61429314e",
                                    StatementType.SUPPORTING,
                                    "Mogę zarabiać dobrze, bez poświęcania siebie."
                            )
                    )
            ),
            // 3
            new StatementDefinition(
                    "17",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_RPGLvK",
                    List.of(
                            new StatementTypeDefinition(
                                    "16e5909f-95fa-49b2-a223-1571c1a1dbed",
                                    StatementType.LIMITING,
                                    "Nie mam z czego oszczędzać."
                            ),
                            new StatementTypeDefinition(
                                    "823413fd-d68c-4d32-a68e-111927619d60",
                                    StatementType.SUPPORTING,
                                    "Mogę odkładać nawet małe kwoty."
                            )
                    )
            ),
            // 4
            new StatementDefinition(
                    "25",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_4r4jAr",
                    List.of(
                            new StatementTypeDefinition(
                                    "ac9a46f8-0dc3-4c99-8fa7-1ca218ae1426",
                                    StatementType.LIMITING,
                                    "Pieniądze się mnie nie trzymają, im więcej zarabiam, tym więcej wydaję."
                            ),
                            new StatementTypeDefinition(
                                    "7e088d96-7fe8-43cf-8f0e-4e43936186ee",
                                    StatementType.SUPPORTING,
                                    "Pieniądze lubią przy mnie zostawać i rosnąć."
                            )
                    )
            ),
            // 5
            new StatementDefinition(
                    "33",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_9QqlpE",
                    List.of(
                            new StatementTypeDefinition(
                                    "68afb718-6fa8-4002-baf4-b0c61e050516",
                                    StatementType.LIMITING,
                                    "„Pieniądze nie rosną na drzewach”."
                            ),
                            new StatementTypeDefinition(
                                    "611ad79c-fcaf-4e9f-b642-55275a87437a",
                                    StatementType.SUPPORTING,
                                    "Pieniądze mogą przychodzić do mnie na wiele sposobów."
                            )
                    )
            ),
            // 6 — (z Tally) „Pieniądze są powodem konfliktów.”
            new StatementDefinition(
                    "41", // numer z etykiety Tally
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_B7zBAe",
                    List.of(
                            new StatementTypeDefinition(
                                    "58bf8302-f303-42e5-a899-a7cc6efc981d",
                                    StatementType.LIMITING,
                                    "Pieniądze są powodem konfliktów."
                            ),
                            new StatementTypeDefinition(
                                    "2ae64bb7-c818-452f-bedb-88a65636ee77",
                                    StatementType.SUPPORTING,
                                    "Pieniądze mogą wspierać harmonię i współpracę."
                            )
                    )
            ),
            // 7 — (z Tally) „Bogactwo nie jest dla mnie.”
            new StatementDefinition(
                    "49", // numer z etykiety Tally
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_Jz90Xr",
                    List.of(
                            new StatementTypeDefinition(
                                    "0f4ab916-b80d-4f0a-b40a-e56b4a7bfdbf",
                                    StatementType.LIMITING,
                                    "Bogactwo nie jest dla mnie."
                            ),
                            new StatementTypeDefinition(
                                    "3d7b117d-a8b9-41e9-89d5-2556f5361f56",
                                    StatementType.SUPPORTING,
                                    "Bogactwo też jest dla mnie."
                            )
                    )
            ),
            // 8
            new StatementDefinition(
                    "57",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_ddWPKr",
                    List.of(
                            new StatementTypeDefinition(
                                    "423e5777-6126-466e-8207-ca8dd3f500e4",
                                    StatementType.LIMITING,
                                    "Oszczędzanie to ciągłe wyrzeczenia."
                            ),
                            new StatementTypeDefinition(
                                    "fe56c74a-fec8-47f5-976e-cf5297d3bd54",
                                    StatementType.SUPPORTING,
                                    "Oszczędzanie i inwestowanie dają mi wolność."
                            )
                    )
            ),
            // 9
            new StatementDefinition(
                    "65",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_VJL8bj",
                    List.of(
                            new StatementTypeDefinition(
                                    "00b533ec-1cfb-415f-91bf-9bd09929a7f4",
                                    StatementType.LIMITING,
                                    "Zarządzam finansami, ale wciąż czuję wewnętrzny opór."
                            ),
                            new StatementTypeDefinition(
                                    "ce290188-e805-455e-936c-385d0e8c0ac0",
                                    StatementType.SUPPORTING,
                                    "Mogę zarządzać finansami z lekkością i spokojem."
                            )
                    )
            ),
            // 10
            new StatementDefinition(
                    "73",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_ZajJ1V",
                    List.of(
                            new StatementTypeDefinition(
                                    "b585949a-8518-4bf5-95e8-228d5486d003",
                                    StatementType.LIMITING,
                                    "Nie rozmawia się o pieniądzach."
                            ),
                            new StatementTypeDefinition(
                                    "a756035a-ee7f-41ae-8b05-86c83c13d423",
                                    StatementType.SUPPORTING,
                                    "Mogę swobodnie rozmawiać o pieniądzach."
                            )
                    )
            ),
            // 11
            new StatementDefinition(
                    "81",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_6N8xWA",
                    List.of(
                            new StatementTypeDefinition(
                                    "aca3154f-2a0f-4d78-8ec7-9d819cfe1c78",
                                    StatementType.LIMITING,
                                    "Lepiej ukrywać pieniądze, żeby ich nie stracić."
                            ),
                            new StatementTypeDefinition(
                                    "542556f0-907a-4460-bfce-a29575f8a16d",
                                    StatementType.SUPPORTING,
                                    "Mogę pokazywać, że mam pieniądze i czuć się z tym bezpiecznie."
                            )
                    )
            ),
            // 12
            new StatementDefinition(
                    "89",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_LpZ0ay",
                    List.of(
                            new StatementTypeDefinition(
                                    "49ddb058-99d8-42b9-86f1-6c74b93f4faf",
                                    StatementType.LIMITING,
                                    "Pieniądze szczęścia nie dają."
                            ),
                            new StatementTypeDefinition(
                                    "1d43178f-9dca-4b2a-b7ab-b8b3888e8b81",
                                    StatementType.SUPPORTING,
                                    "Pieniądze wspierają moje szczęście i spokój."
                            )
                    )
            ),
            // 13
            new StatementDefinition(
                    "97",
                    StatementCategory.SCARCITY_GUARDIAN,
                    "question_8x1e6l",
                    List.of(
                            new StatementTypeDefinition(
                                    "382ef4f3-5901-4057-a1e2-bdc73f127bee",
                                    StatementType.LIMITING,
                                    "Zawsze, gdy zaczynam mieć więcej pieniędzy, zaraz coś się dzieje i muszę je wydać."
                            ),
                            new StatementTypeDefinition(
                                    "7ec85154-ea44-4995-ae7f-320a473e48be",
                                    StatementType.SUPPORTING,
                                    "Nawet kiedy życie zaskakuje, mogę czuć się bezpiecznie finansowo."
                            )
                    )
            )
    );

    public static final List<StatementDefinition> ALL =
            Stream.of(
                            SCARCITY_GUARDIAN,
                            SELF_SUFFICIENT_SHIELD,
                            FROZEN_VISIONARY,
                            LOYAL_HEIRESS,
                            WITHDRAWN_LEADER,
                            OVERWORKED_PERFECTIONIST,
                            BLOCKED_IN_RECEIVING,
                            MODESTY_IDEALIST
                    )
                    .flatMap(List::stream)
                    .toList();

    private static final Map<String, StatementDefinition> BY_ID =
            ALL.stream().collect(Collectors.toUnmodifiableMap(
                    d -> d.getStatementId().trim().toLowerCase(),
                    Function.identity()
            ));

    private static final Map<String, StatementDefinition> BY_KEY =
            ALL.stream().collect(Collectors.toUnmodifiableMap(
                    d -> d.getStatementKey().trim().toLowerCase(),
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

    public static StatementDefinition requireByKey(String key) {
        if (key == null) {
            throw new IllegalArgumentException("statementId is null");
        }
        StatementDefinition def = BY_KEY.get(key.trim().toLowerCase());
        if (def == null) {
            throw new IllegalArgumentException("Unknown statementId: " + key);
        }
        return def;
    }

}
