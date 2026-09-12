package com.app.auction.controller;

import org.springframework.web.bind.annotation.RestController;

import com.app.auction.dto.AuctionItemDto;
import com.app.auction.sao.Middleware;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
@RequestMapping("v1/adm")
@RequiredArgsConstructor
@Validated
public class MainController {

    private final Middleware middleware;

    @PostMapping("add")
    public ResponseEntity<Object> addItem(@RequestBody @Valid AuctionItemDto.AddRequest req) {
        return ResponseEntity.ok().body(middleware.addItem(req));
    }

    @GetMapping("summary/{id}")
    public ResponseEntity<Object> getSummary(@PathVariable Long id) {
        return ResponseEntity.ok().body(middleware.getSummary(id));
    }

}
