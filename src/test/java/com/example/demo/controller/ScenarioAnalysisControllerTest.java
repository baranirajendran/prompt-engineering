
package com.example.demo.controller;

import com.example.demo.model.ScenarioAnalysisRequest;
import com.example.demo.model.ScenarioAnalysisResponse;
import com.example.demo.service.AiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScenarioAnalysisController.class)
public class ScenarioAnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AiService aiService;

    @Autowired
    private ObjectMapper objectMapper;

    
@Test
public void testCase1_EducationReformInitiative() throws Exception {
    ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
    request.setScenario("A school district aims to implement hybrid learning models across 15 rural schools");
    request.setConstraints(List.of(
        "Limited broadband infrastructure", "40% teacher resistance to technology adoption", "state-mandated testing requirements", "Audience Level: Intermediate"
    ));

    ScenarioAnalysisResponse mockResponse = new ScenarioAnalysisResponse();
    mockResponse.setScenarioSummary("Test Summary for Education Reform Initiative");
    mockResponse.setPotentialPitfalls(List.of("Example Risk"));
    mockResponse.setProposedStrategies("Example Strategy");
    mockResponse.setRecommendedResources(List.of("Example Tool"));
    mockResponse.setDisclaimer("Test Disclaimer");

    when(aiService.generateAnalysis(Mockito.any())).thenReturn(mockResponse);

    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.scenarioSummary").value("Test Summary for Education Reform Initiative"));
}

@Test
public void testCase2_HealthcareSystemModernization() throws Exception {
    ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
    request.setScenario("A multi-specialty clinic needs to adopt telemedicine for chronic disease management");
    request.setConstraints(List.of(
        "HIPAA compliance requirements", "60% patient base over 65", "existing EHR system incompatible with video conferencing", "Audience Level: Expert"
    ));

    ScenarioAnalysisResponse mockResponse = new ScenarioAnalysisResponse();
    mockResponse.setScenarioSummary("Test Summary for Healthcare System Modernization");
    mockResponse.setPotentialPitfalls(List.of("Example Risk"));
    mockResponse.setProposedStrategies("Example Strategy");
    mockResponse.setRecommendedResources(List.of("Example Tool"));
    mockResponse.setDisclaimer("Test Disclaimer");

    when(aiService.generateAnalysis(Mockito.any())).thenReturn(mockResponse);

    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.scenarioSummary").value("Test Summary for Healthcare System Modernization"));
}

@Test
public void testCase3_EnvironmentalSustainabilityProject() throws Exception {
    ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
    request.setScenario("Coastal city plans to reduce plastic waste by 75% within 3 years");
    request.setConstraints(List.of(
        "$2M annual budget", "strong tourism industry opposition", "existing recycling infrastructure only handles PET plastics", "Audience Level: Intermediate"
    ));

    ScenarioAnalysisResponse mockResponse = new ScenarioAnalysisResponse();
    mockResponse.setScenarioSummary("Test Summary for Environmental Sustainability Project");
    mockResponse.setPotentialPitfalls(List.of("Example Risk"));
    mockResponse.setProposedStrategies("Example Strategy");
    mockResponse.setRecommendedResources(List.of("Example Tool"));
    mockResponse.setDisclaimer("Test Disclaimer");

    when(aiService.generateAnalysis(Mockito.any())).thenReturn(mockResponse);

    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.scenarioSummary").value("Test Summary for Environmental Sustainability Project"));
}

@Test
public void testCase4_NonProfitCapacityBuilding() throws Exception {
    ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
    request.setScenario("Regional food bank seeks 30% donor retention improvement");
    request.setConstraints(List.of(
        "3-person development team", "declining corporate contributions", "aging volunteer base", "Audience Level: Beginner"
    ));

    ScenarioAnalysisResponse mockResponse = new ScenarioAnalysisResponse();
    mockResponse.setScenarioSummary("Test Summary for Non-Profit Capacity Building");
    mockResponse.setPotentialPitfalls(List.of("Example Risk"));
    mockResponse.setProposedStrategies("Example Strategy");
    mockResponse.setRecommendedResources(List.of("Example Tool"));
    mockResponse.setDisclaimer("Test Disclaimer");

    when(aiService.generateAnalysis(Mockito.any())).thenReturn(mockResponse);

    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.scenarioSummary").value("Test Summary for Non-Profit Capacity Building"));
}

@Test
public void testCase5_TechConferenceOrganization() throws Exception {
    ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
    request.setScenario("Planning a 5,000-attendee developer conference with net-zero emissions");
    request.setConstraints(List.of(
        "$1.2M budget", "must use existing convention center", "40% international attendees", "Audience Level: Intermediate"
    ));

    ScenarioAnalysisResponse mockResponse = new ScenarioAnalysisResponse();
    mockResponse.setScenarioSummary("Test Summary for Tech Conference Organization");
    mockResponse.setPotentialPitfalls(List.of("Example Risk"));
    mockResponse.setProposedStrategies("Example Strategy");
    mockResponse.setRecommendedResources(List.of("Example Tool"));
    mockResponse.setDisclaimer("Test Disclaimer");

    when(aiService.generateAnalysis(Mockito.any())).thenReturn(mockResponse);

    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.scenarioSummary").value("Test Summary for Tech Conference Organization"));
}

@Test
public void testCase6_FinancialSystemMigration() throws Exception {
    ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
    request.setScenario("Legacy banking platform transition to cloud-native infrastructure");
    request.setConstraints(List.of(
        "Zero downtime tolerance", "PCI DSS compliance", "COBOL-based core systems", "Audience Level: Expert"
    ));

    ScenarioAnalysisResponse mockResponse = new ScenarioAnalysisResponse();
    mockResponse.setScenarioSummary("Test Summary for Financial System Migration");
    mockResponse.setPotentialPitfalls(List.of("Example Risk"));
    mockResponse.setProposedStrategies("Example Strategy");
    mockResponse.setRecommendedResources(List.of("Example Tool"));
    mockResponse.setDisclaimer("Test Disclaimer");

    when(aiService.generateAnalysis(Mockito.any())).thenReturn(mockResponse);

    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.scenarioSummary").value("Test Summary for Financial System Migration"));
}

@Test
public void testCase7_ProductLaunchStrategy() throws Exception {
    ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
    request.setScenario("Introducing smart home devices in saturated IoT market");
    request.setConstraints(List.of(
        "$500K marketing budget", "established competitors with 80% market share", "need App Store approval", "Audience Level: Intermediate"
    ));

    ScenarioAnalysisResponse mockResponse = new ScenarioAnalysisResponse();
    mockResponse.setScenarioSummary("Test Summary for Product Launch Strategy");
    mockResponse.setPotentialPitfalls(List.of("Example Risk"));
    mockResponse.setProposedStrategies("Example Strategy");
    mockResponse.setRecommendedResources(List.of("Example Tool"));
    mockResponse.setDisclaimer("Test Disclaimer");

    when(aiService.generateAnalysis(Mockito.any())).thenReturn(mockResponse);

    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.scenarioSummary").value("Test Summary for Product Launch Strategy"));
}

@Test
public void testCase8_PersonalDebtManagement() throws Exception {
    ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
    request.setScenario("Recent graduates with $150K combined student loans seeking repayment strategy");
    request.setConstraints(List.of(
        "$4K monthly income", "variable freelance earnings", "high-cost urban living expenses", "Audience Level: Beginner"
    ));

    ScenarioAnalysisResponse mockResponse = new ScenarioAnalysisResponse();
    mockResponse.setScenarioSummary("Test Summary for Personal Debt Management");
    mockResponse.setPotentialPitfalls(List.of("Example Risk"));
    mockResponse.setProposedStrategies("Example Strategy");
    mockResponse.setRecommendedResources(List.of("Example Tool"));
    mockResponse.setDisclaimer("Test Disclaimer");

    when(aiService.generateAnalysis(Mockito.any())).thenReturn(mockResponse);

    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.scenarioSummary").value("Test Summary for Personal Debt Management"));
}

@Test
public void testCase9_UrbanTransportationPolicy() throws Exception {
    ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
    request.setScenario("Implementing congestion pricing in metropolitan area");
    request.setConstraints(List.of(
        "Political opposition from suburban communities", "existing public transit capacity limits", "federal funding requirements", "Audience Level: Intermediate"
    ));

    ScenarioAnalysisResponse mockResponse = new ScenarioAnalysisResponse();
    mockResponse.setScenarioSummary("Test Summary for Urban Transportation Policy");
    mockResponse.setPotentialPitfalls(List.of("Example Risk"));
    mockResponse.setProposedStrategies("Example Strategy");
    mockResponse.setRecommendedResources(List.of("Example Tool"));
    mockResponse.setDisclaimer("Test Disclaimer");

    when(aiService.generateAnalysis(Mockito.any())).thenReturn(mockResponse);

    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.scenarioSummary").value("Test Summary for Urban Transportation Policy"));
}

@Test
public void testCase10_SaaSStartupScaling() throws Exception {
    ScenarioAnalysisRequest request = new ScenarioAnalysisRequest();
    request.setScenario("B2B analytics platform expanding to European markets");
    request.setConstraints(List.of(
        "GDPR compliance needs", "6-person engineering team", "competing with established incumbents", "Audience Level: Intermediate"
    ));

    ScenarioAnalysisResponse mockResponse = new ScenarioAnalysisResponse();
    mockResponse.setScenarioSummary("Test Summary for SaaS Startup Scaling");
    mockResponse.setPotentialPitfalls(List.of("Example Risk"));
    mockResponse.setProposedStrategies("Example Strategy");
    mockResponse.setRecommendedResources(List.of("Example Tool"));
    mockResponse.setDisclaimer("Test Disclaimer");

    when(aiService.generateAnalysis(Mockito.any())).thenReturn(mockResponse);

    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.scenarioSummary").value("Test Summary for SaaS Startup Scaling"));
}

@Test
public void testCase11_NegativeTestCases() throws Exception {
    // Test 1: Missing scenario field
    String missingScenarioJson = "{\"constraints\": [\"Budget: $10,000\", \"Timeline: 4 weeks\"]}";
    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(missingScenarioJson))
            .andExpect(status().isBadRequest());

    // Test 2: Missing constraints field
    String missingConstraintsJson = "{\"scenario\": \"Build an app\"}";
    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(missingConstraintsJson))
            .andExpect(status().isBadRequest());

    // Test 3: Invalid JSON format
    String invalidJson = "{scenario: 'Build an app', constraints: [\"Budget\": \"$5000\"]}";
    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidJson))
            .andExpect(status().isBadRequest());

    // Test 4: Empty request body
    mockMvc.perform(post("/api/analyze-scenario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(""))
            .andExpect(status().isBadRequest());
}
}