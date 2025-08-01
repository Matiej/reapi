package com.emat.reapi.ai.port;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.transformer.splitter.TextSplitter;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TextChunksSplitterTest {
    private TextChunksSplitter textSplitter;

    @BeforeEach
    void setUp() {
        textSplitter = new TextChunksSplitter();
    }

    @Test
    void shouldSplitTextByFixedLength() {
        // given
        String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int maxLength = 10;

        // when
        List<String> chunks = textSplitter.splitByLength(text, maxLength);

        // then
        assertThat(chunks).hasSize(4);
        assertThat(chunks).containsExactly(
                "ABCDEFGHIJ",
                "KLMNOPQRST",
                "UVWXYZ1234",
                "567890"
        );
    }

    @Test
    void shouldSplitTextBySentences() {
        // given
        String text = "Hello. This is a test. It should split at sentence boundaries! Right?";
        int maxChunkLength = 30;

        // when
        List<String> chunks = textSplitter.splitBySentences(text, maxChunkLength);

        // then
        assertThat(chunks).hasSize(2);
        assertThat(chunks.get(0)).endsWith(".");
        assertThat(chunks.get(1)).endsWith("?");
    }

    @Test
    void shouldHandleTextShorterThanChunkSize() {
        // given
        String text = "Short text.";
        int maxLength = 100;

        // when
        List<String> chunks = textSplitter.splitByLength(text, maxLength);

        // then
        assertThat(chunks).hasSize(1);
        assertThat(chunks.get(0)).isEqualTo("Short text.");
    }

    @Test
    void shouldSplitLongSentenceWhenNoPunctuationFound() {
        // given
        String text = "This text has no sentence-ending punctuation and should be split by chunk size only";
        int maxChunkLength = 30;

        // when
        List<String> chunks = textSplitter.splitBySentences(text, maxChunkLength);

        // then
        assertThat(chunks).isNotEmpty();
        assertThat(chunks.stream().mapToInt(String::length).max().orElse(0)).isLessThanOrEqualTo(maxChunkLength);
    }

    @Test
    void shouldNotReturnEmptyChunks() {
        // given
        String text = "....";
        int maxChunkLength = 2;

        // when
        List<String> chunks = textSplitter.splitBySentences(text, maxChunkLength);

        // then
        assertThat(chunks).allMatch(chunk -> !chunk.isBlank());
    }

    @Test
    void shouldRespectMaxToleranceExtension() {
        // given
        String text = "A chunk. Another sentence right beyond limit!";
        int maxLength = 20; //

        // when
        List<String> chunks = textSplitter.splitBySentences(text, maxLength);

        // then
        assertThat(chunks).hasSize(3);
    }

    @Test
    void shouldSplitWhenSentenceExactlyAtToleranceEdge() {
        // given
        String text = "Aa sentence ends right.";
        int maxLength = 21;

        // when
        List<String> chunks = textSplitter.splitBySentences(text, maxLength);

        // then
        assertThat(chunks).hasSize(1);
        assertThat(chunks.get(0)).endsWith(".");
    }

    @Test
    void shouldSplitMultipleSentencesWithNoGaps() {
        // given
        String text = "One. Two! Three? Four. Five.";
        int maxLength = 15;

        // when
        List<String> chunks = textSplitter.splitBySentences(text, maxLength);

        // then
        var expected = text.replaceAll(" ", "");
        var result = String.join("", chunks).replaceAll(" ", "");
        assertThat(chunks).hasSize(2);
        assertThat(result).isEqualTo(expected);
    }
}