package com.example.minecraft.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@Tag(name = "metrics")
public class RedirectController {

    @GetMapping
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }

    @Operation(summary = "basicstat 조회", description = "basicstat 조회")
    @GetMapping("metrics")
    public String metrics() {
        return "redirect:/actuator/prometheus";
    }
}
