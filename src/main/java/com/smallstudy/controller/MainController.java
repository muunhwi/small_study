package com.smallstudy.controller;

import com.smallstudy.domain.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    @GetMapping("/main")
    String mainGet() {
        return "/main";
    }
}
