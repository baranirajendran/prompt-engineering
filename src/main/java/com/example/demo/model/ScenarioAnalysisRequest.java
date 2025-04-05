package com.example.demo.model;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ScenarioAnalysisRequest {
    @NotNull(message = "Scenario must not be null")
    @NotEmpty(message = "Scenario must not be empty")
    private String scenario;

    @NotNull(message = "Constraints must not be null")
    @NotEmpty(message = "Constraints must not be empty")
    private List<String> constraints;

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public List<String> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<String> constraints) {
        this.constraints = constraints;
    }
}