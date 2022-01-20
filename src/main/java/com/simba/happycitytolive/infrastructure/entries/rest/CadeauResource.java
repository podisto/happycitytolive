package com.simba.happycitytolive.infrastructure.entries.rest;

import com.simba.happycitytolive.application.usecases.AttributionCadeauService;
import lombok.RequiredArgsConstructor;
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
public class CadeauResource {

    private final AttributionCadeauService attributionCadeauService;

    @PostMapping("/attribuer")
    public ResponseEntity<Void> attribuer() {
        attributionCadeauService.attribuer();
        return ResponseEntity.ok().build();
    }
}
