package com.example.demo.model;

import java.util.List;
import java.util.Map;

public class ScenarioAnalysisResponse {
    private String scenarioSummary;
    private List<String> potentialPitfalls;
    private String proposedStrategies;
    private List<String> recommendedResources;
    private String disclaimer;

    private Map<String, List<String>> recommendedResourcesStructured;

    public Map<String, List<String>> getRecommendedResourcesStructured() {
        return recommendedResourcesStructured;
    }
    
    public void setRecommendedResourcesStructured(Map<String, List<String>> recommendedResourcesStructured) {
        this.recommendedResourcesStructured = recommendedResourcesStructured;
    }

    public String getScenarioSummary() {
        return scenarioSummary;
    }

    public void setScenarioSummary(String scenarioSummary) {
        this.scenarioSummary = scenarioSummary;
    }

    public List<String> getPotentialPitfalls() {
        return potentialPitfalls;
    }

    public void setPotentialPitfalls(List<String> potentialPitfalls) {
        this.potentialPitfalls = potentialPitfalls;
    }

    public String getProposedStrategies() {
        return proposedStrategies;
    }
    
    public void setProposedStrategies(String proposedStrategies) {
        this.proposedStrategies = proposedStrategies;
    }

    public List<String> getRecommendedResources() {
        return recommendedResources;
    }

    public void setRecommendedResources(List<String> recommendedResources) {
        this.recommendedResources = recommendedResources;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }
}