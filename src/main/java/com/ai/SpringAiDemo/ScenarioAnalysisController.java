package com.ai.SpringAiDemo;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/scenario")
public class ScenarioAnalysisController {

    private final AiService aiService;

    public ScenarioAnalysisController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/analyze")
    public ScenarioAnalysisResponse analyzeScenario(
            @RequestBody ScenarioAnalysisRequest request) {
        return aiService.generateAnalysis(request);
    }

     //Optional test endpoint using GET with URL params (if needed for quick testing)
    @GetMapping("/test")
    public ScenarioAnalysisResponse analyzeScenarioTest(
            @RequestParam String scenario,
            @RequestParam String constraints) {
        // Assume constraints passed as comma-separated string
        ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
        request.setScenario(scenario);
        request.setConstraints(List.of(constraints.split(",")));
        return aiService.generateAnalysis(request);
    }
}

