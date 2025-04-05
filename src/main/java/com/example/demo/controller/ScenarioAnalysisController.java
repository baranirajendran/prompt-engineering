package com.example.demo.controller;

import jakarta.validation.Valid;
import com.example.demo.model.ScenarioAnalysisRequest;
import com.example.demo.model.ScenarioAnalysisResponse;
import com.example.demo.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScenarioAnalysisController {

    @Autowired
    private AiService aiService;

    @PostMapping("/analyze-scenario")
    public ResponseEntity<ScenarioAnalysisResponse> analyzeScenario(@Valid @RequestBody ScenarioAnalysisRequest request) {
        ScenarioAnalysisResponse response = aiService.generateAnalysis(request);
        return ResponseEntity.ok(response);
    }
}