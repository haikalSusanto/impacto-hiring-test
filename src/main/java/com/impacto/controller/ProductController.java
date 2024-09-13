package com.impacto.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    @GetMapping("")
    public ResponseEntity<Object> getProduct() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "ok");
        return ResponseEntity
                .ok((Object) response);
    }

}
