package com.emat.reapi.api;

import com.emat.reapi.ai.TtsRequest;
import com.emat.reapi.ai.port.ReApiTtsService;
import com.emat.reapi.ai.validator.TextFileValidatorFactory;
import com.emat.reapi.api.dto.TtsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.openai.api.OpenAiAudioApi.SpeechRequest.AudioResponseFormat;
import org.springframework.ai.openai.api.OpenAiAudioApi.SpeechRequest.Voice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("reapi/tts")
@Slf4j
@AllArgsConstructor
@Tag(name = "TTS", description = "Text-to-Speech generation with OpenAI")
@Validated
public class TtsController {
    private final ReApiTtsService ttsService;
    private final TextFileValidatorFactory speechFileValidator;

    @Operation(
            summary = "Generate speech from text",
            description = "Converts input text to speech using OpenAI's TTS model. Returns the audio as a downloadable file.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Audio generated successfully",
                            content = @Content(
                                    mediaType = "application/octet-stream",
                                    schema = @Schema(implementation = TtsResponse.class)
                            )),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "500", description = "Server error")
            }
    )
    @GetMapping(value = "/text", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<ResponseEntity<byte[]>> generateBasicSpeech(
            @Parameter(
                    description = "Input text to convert to speech (max 1000 characters)",
                    required = true,
                    schema = @Schema(type = "string", maxLength = 1000)
            )
            @RequestParam @Size(max = 1000) String text,

            @Parameter(
                    description = """
                            Voice used for synthesis.
                            Recommended: `nova`, `shimmer`, `echo`, `onyx`.
                            For full list, check OpenAI documentation.
                            """,
                    schema = @Schema(implementation = Voice.class),
                    example = "nova"
            )
            @RequestParam Voice voice,

            @Parameter(
                    description = """
                            Audio output format.
                            Supported: `mp3`, `opus`, `aac`, `flac`, `pcm`.
                            """
            )
            @RequestParam(defaultValue = "mp3") String format,

            @Parameter(
                    description = """
                            Whether to use high-definition voice model.
                            `false` → cheaper model (`tts-1`), 
                            `true` → better quality (`tts-1-hd`, higher cost).
                            """
            )
            @RequestParam(defaultValue = "false") String hdRaw
    ) {
        log.info("received request: GET /reapi/tts/text");
        boolean hd = Boolean.parseBoolean(hdRaw);
        AudioResponseFormat audioFormat = mapFormat(format);

        Mono<byte[]> audioMono = ttsService.generateSpeech(new TtsRequest(text, hd, voice, audioFormat));

        return audioMono.map(audioBytes -> {
            String fileName = "speech." + format;
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, audioFormatToMimeType(audioFormat))
                    .body(audioBytes);
        });
    }


    @Operation(
            summary = "Generate speech from uploaded .txt file",
            description = """
                    Converts uploaded `.txt` file into speech using OpenAI's TTS model.
                    File must be of MIME type `text/plain` and contain no more than 4000 characters.
                    Returns audio file in selected format (e.g. mp3).
                    """,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(
                                    type = "object",
                                    description = "Multipart/form-data payload with .txt file"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Audio generated successfully",
                            content = @Content(mediaType = "application/octet-stream",
                                    schema = @Schema(implementation = TtsResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid file or request parameters"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<ResponseEntity<byte[]>> generateSpeechFromFile(
            @RequestPart("file")
            @Parameter(
                    description = "Text file (.txt, UTF-8) with max 4000 characters",
                    required = true,
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
            MultipartFile file,
            @RequestParam(defaultValue = "nova")
            @Parameter(
                    description = """
                            Voice used for synthesis.
                            Recommended: `nova`, `shimmer`, `echo`, `onyx`.
                            """,
                    example = "nova"
            )
            Voice voice,
            @RequestParam(defaultValue = "mp3")
            @Parameter(
                    description = """
                            Audio output format.
                            Supported: `mp3`, `opus`, `aac`, `flac`, `pcm`.
                            """,
                    schema = @Schema(allowableValues = {"mp3", "opus", "aac", "flac", "pcm"})
            )
            String format,
            @RequestParam(defaultValue = "false")
            @Parameter(
                    description = """
                            Whether to use high-definition voice model.
                            `false` → cheaper model (`tts-1`),
                            `true` → higher quality model (`tts-1-hd`, more expensive).
                            """
            )
            boolean hd
    ) {
        return Mono.fromCallable(() -> {
            log.info("received request: GET /reapi/tts/file");
            AudioResponseFormat audioFormat = mapFormat(format);
            Mono<byte[]> audioMono = ttsService.generateSpeech(new TtsRequest(file, hd, voice, audioFormat));

            return audioMono.map(audioBytes -> {
                String fileName = "speech." + format;
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, audioFormatToMimeType(audioFormat))
                        .body(audioBytes);
            });
        }).flatMap(mono -> mono);
    }

    private AudioResponseFormat mapFormat(String format) {
        return switch (format.toLowerCase()) {
            case "mp3" -> AudioResponseFormat.MP3;
            case "opus" -> AudioResponseFormat.OPUS;
            case "aac" -> AudioResponseFormat.AAC;
            case "flac" -> AudioResponseFormat.FLAC;
            case "pcm" -> AudioResponseFormat.PCM;
            default -> AudioResponseFormat.MP3;
        };
    }

    private String audioFormatToMimeType(AudioResponseFormat format) {
        return switch (format) {
            case MP3 -> "audio/mpeg";
            case OPUS -> "audio/opus";
            case AAC -> "audio/aac";
            case FLAC -> "audio/flac";
            case PCM -> "audio/wav";
            case WAV -> "audio/wav";
        };
    }
}
