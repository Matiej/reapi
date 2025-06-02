package com.emat.reapi.api;

import com.emat.reapi.api.dto.ApplicationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publish")
public class SubController {
    private static final Logger log = LoggerFactory.getLogger(SubController.class);
    private final ApplicationData applicationData;

    public SubController(ApplicationData applicationData) {
        this.applicationData = applicationData;
    }

    @GetMapping("/version")
    ResponseEntity<AppDataResponse> getVersion() {
        log.info("Receive '/version/' request");
        return ResponseEntity
                .status(200)
                .body(new AppDataResponse(applicationData.name(), applicationData.version()));
    }
}

record AppDataResponse(
        String name,
        String version
) {
}
