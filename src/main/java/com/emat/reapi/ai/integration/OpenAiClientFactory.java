package com.emat.reapi.ai.integration;

import com.emat.reapi.ai.configuration.OpenAiParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.openai.*;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class OpenAiClientFactory {
    private static final int MAX_TOKENS = 240;
    private static final double TEMPERATURE = 0.7;
    private static final Map<OpenAiApi.ChatModel, OpenAiChatModel> openAiChatModelMap = new EnumMap<>(OpenAiApi.ChatModel.class);
    private final OpenAiParams opeApiParams;
    private final WebClient.Builder openAiWebClinetBuilder;
    private final RestClient.Builder restClientBuilder;

    public OpenAiClientFactory(
            OpenAiParams opeApiParams,
            @Qualifier("openAiWebClientBuilder") WebClient.Builder openAiWebClinetBuilder,
            @Qualifier("openAiRestClientBuilder") RestClient.Builder restClientBuilder) {
        this.opeApiParams = opeApiParams;
        this.openAiWebClinetBuilder = openAiWebClinetBuilder;
        this.restClientBuilder = restClientBuilder;
        initializeOpenAiChatModels();
    }

    private void initializeOpenAiChatModels() {
        for (OpenAiApi.ChatModel chatModelEnum : OpenAiApi.ChatModel.values()) {
            var options = OpenAiChatOptions.builder()
                    .model(chatModelEnum)
                    .maxTokens(MAX_TOKENS)
                    .temperature(TEMPERATURE)
                    .logprobs(true) // Enable log probabilities
                    .build();

            var openAiChatModel = OpenAiChatModel.builder()
                    .openAiApi(buildOpenAiApi())
                    .defaultOptions(options)
                    .build();
            openAiChatModelMap.put(chatModelEnum, openAiChatModel);
        }
    }

    public OpenAiChatModel createChatModel(OpenAiApi.ChatModel chatModel) {
        return openAiChatModelMap.get(chatModel);
    }

    public OpenAiAudioSpeechModel createAudioSpeechModel(
            OpenAiAudioApi.SpeechRequest.Voice voice,
            OpenAiAudioApi.SpeechRequest.AudioResponseFormat audioFormat,
            OpenAiAudioApi.TtsModel ttsModel
    ) {
        var options = OpenAiAudioSpeechOptions.builder()
                .voice(voice.value.toLowerCase())
                .speed(1.0f)
                .responseFormat(audioFormat)
                .model(ttsModel.value)
                .build();

        var audioApi = OpenAiAudioApi.builder()
                .baseUrl("https://api.openai.com/")
                .apiKey(opeApiParams.getOpenApiKey())
                .webClientBuilder(openAiWebClinetBuilder)
                .build();

        RetryTemplate retryTemplate = RetryTemplate.builder()
                .maxAttempts(1)
                .build();
        return new OpenAiAudioSpeechModel(audioApi, options, retryTemplate);
    }

    public OpenAiEmbeddingModel createEmbeddingModel() {
        var options = OpenAiEmbeddingOptions.builder()
                .model("text-embedding-3-small")
                .build();
        var meta = MetadataMode.ALL;

        return new OpenAiEmbeddingModel(buildOpenAiApi(), meta, options);
    }

    public OpenAiImageModel createImageModel() {
        var options = OpenAiImageOptions.builder()
                .model("dall-e-3")
                .quality("hd")
                .N(1)
                .build();
        var imageApi = OpenAiImageApi.builder()
                .apiKey(opeApiParams.getOpenApiKey())
                .baseUrl(opeApiParams.getBaseUrl())
                .restClientBuilder(restClientBuilder)
                .build();
        var retryTemplate = RetryTemplate.defaultInstance();
        return new OpenAiImageModel(imageApi, options, retryTemplate);
    }

    public OpenAiChatModel createChatModelWithFunctions(
            OpenAiApi.ChatModel chatModel,
            List<ToolCallback> toolCallbackList
    ) {

        var options = OpenAiChatOptions.builder()
                .model(chatModel)
                .maxTokens(MAX_TOKENS)
                .temperature(TEMPERATURE)
                .toolCallbacks(toolCallbackList)
                .build();

        return OpenAiChatModel.builder()
                .openAiApi(buildOpenAiApi())
                .defaultOptions(options)
                .build();
    }

    private OpenAiApi buildOpenAiApi() {
        return OpenAiApi.builder()
                .baseUrl(opeApiParams.getBaseUrl())
                .apiKey(opeApiParams.getOpenApiKey())
                .webClientBuilder(openAiWebClinetBuilder)
                .build();
    }
}
