package com.simba.happycitytolive.infrastructure.entries.rest;

import com.simba.happycitytolive.application.usecases.AttributionCadeauService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by podisto on 19/01/2022.
 */
@RestController
@RequestMapping(value = "/api/cadeaux")
@RequiredArgsConstructor
@Slf4j
public class CadeauResource {

    private final AttributionCadeauService attributionCadeauService;

    @PostMapping("/attribuer")
    public ResponseEntity<Void> attribuerCadeaux() {
        log.info("Attribuer Cadeau");
        attributionCadeauService.attribuerCadeaux();
        return ResponseEntity.ok().build();
    }
}
