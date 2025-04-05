package com.example.demo.service;

import com.example.demo.config.GeminiConfig;
import com.example.demo.config.OpenAiConfig;
import com.example.demo.model.OpenAiRequest;
import com.example.demo.model.OpenAiMessage;
import com.example.demo.model.ScenarioAnalysisRequest;
import com.example.demo.model.ScenarioAnalysisResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AiService {

    // @Autowired
    // private OpenAiConfig openAiConfig;

    // private final RestTemplate restTemplate = new RestTemplate();
    // private final ObjectMapper objectMapper = new ObjectMapper();

    // public ScenarioAnalysisResponse generateAnalysis(ScenarioAnalysisRequest request) {
    //     String prompt = buildPrompt(request);
    //     System.out.println("Using OpenAI model: " + openAiConfig.getModel());
    //     // ✅ Use a Java object instead of raw JSON string
    //     OpenAiRequest openAiRequest = new OpenAiRequest(
    //         openAiConfig.getModel(),
    //         List.of(new OpenAiMessage("user", prompt))
    //     );
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);
    //     headers.setBearerAuth(openAiConfig.getApiKey());

    //     HttpEntity<OpenAiRequest> entity = new HttpEntity<>(openAiRequest, headers);

    //     ResponseEntity<String> responseEntity = restTemplate.exchange(
    //             "https://api.openai.com/v1/chat/completions",
    //             HttpMethod.POST,
    //             entity,
    //             String.class
    //     );

    //     String content;
    //     try {
    //         JsonNode root = objectMapper.readTree(responseEntity.getBody());
    //         content = root.path("choices").get(0).path("message").path("content").asText();
    //     } catch (Exception e) {
    //         throw new RuntimeException("Failed to parse OpenAI response", e);
    //     }

    //     return parseApiResponse(content);
    // }

    @Autowired
    private GeminiConfig geminiConfig;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ScenarioAnalysisResponse generateAnalysis(ScenarioAnalysisRequest request) {
        String prompt = buildPrompt(request);
        System.out.println("Barani :" + prompt);
        String url = String.format(
                "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent?key=%s",
                geminiConfig.getModel(), geminiConfig.getApiKey()
        );

        Map<String, Object> body = new HashMap<>();
        Map<String, Object> message = new HashMap<>();
        message.put("parts", List.of(Map.of("text", prompt)));
        body.put("contents", List.of(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url, HttpMethod.POST, entity, String.class
        );

        String content;
        try {
            JsonNode root = objectMapper.readTree(responseEntity.getBody());
            content = root.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gemini response", e);
        }

        System.out.println("🔍 Gemini raw content:\n" + content);
        return parseApiResponse(content); 
    }

    private String buildPrompt(ScenarioAnalysisRequest request) {
        return "Given the following scenario and constraints, generate a structured analysis that includes:\n"
                + "1. Executive Summary: Provide a concise overview (2-3 sentences) capturing the core challenge and key constraints.\n"
                + "2. Critical Risks & Pitfalls: Identify 3-5 potential obstacles or challenges in order of severity, with brief explanations of why each matters.\n"
                + "3. Strategic Recommendations: For each identified pitfall, provide a corresponding strategy, including:\n"
                + "   - Immediate action steps\n"
                + "   - Success indicators\n"
                + "   - Estimated implementation difficulty (Low/Medium/High)\n"
                + "4. Resource Requirements:\n"
                + "   - Essential tools or platforms (specify free vs. paid options)\n"
                + "   - Knowledge resources or learning materials\n"
                + "   - Potential expert consultations or community support\n"
                + "5. Limitations & Considerations: Include 2-3 sentences addressing relevant ethical considerations, implementation challenges, and when expert consultation is necessary.\n"
                + "6. Implementation Timeline: Break down recommendation execution into:\n"
                + "   - Immediate actions (within 1 week)\n"
                + "   - Short-term initiatives (1-3 months)\n"
                + "   - Long-term strategies (3+ months)\n"
                + "7. ROI Projection: For each strategy, estimate:\n"
                + "   - Implementation costs (Low/Medium/High)\n"
                + "   - Potential benefits (Low/Medium/High)\n"
                + "   - Approximate timeframe to positive return\n"
                + "8. Alternative Approaches: Briefly outline 2-3 alternative strategies, including:\n"
                + "   - Key differentiators from primary recommendations\n"
                + "   - Scenarios where this alternative might be preferable\n"
                + "   - Major tradeoffs involved\n"
                + "\n"
                + "Adapt your response for AUDIENCE LEVEL: Intermediate with TONE: Practical guidance.\n\n"
                + "\n"
                + "Scenario: " + request.getScenario() + "\n"
                + "Constraints: " + String.join(", ", request.getConstraints());
    }

    private ScenarioAnalysisResponse parseApiResponse(String content) {
    ScenarioAnalysisResponse response = new ScenarioAnalysisResponse();

    // Use regex-friendly section headers
    String[] sections = content.split("\\*\\*\\d+\\.\\s");

    if (sections.length < 5) {
        System.out.println("⚠️ Unexpected format in Gemini response. Sections found: " + sections.length);
        return response;
    }

    response.setScenarioSummary(sections[1].replaceAll("\\*\\*", "").trim());
    response.setPotentialPitfalls(extractBulletPoints(sections[2]));
    response.setProposedStrategies(extractBulletPoints(sections[3]));
    response.setRecommendedResourcesStructured(extractStructuredResources(sections[4]));

    String disclaimer = sections.length >= 6 ? sections[5].trim() : "This is AI-generated content.";
    response.setDisclaimer(disclaimer.replaceAll("\\*\\*", "").trim());

    return response;
    }

    private Map<String, List<String>> extractStructuredResources(String section) {
        Map<String, List<String>> structuredResources = new LinkedHashMap<>();
        String currentSection = "general";
        List<String> items = new ArrayList<>();
        for (String line : section.split("\\n")) {
            line = line.trim();
            if (line.endsWith(":")) {
                if (!items.isEmpty()) {
                    structuredResources.put(currentSection, new ArrayList<>(items));
                    items.clear();
                }
                currentSection = line.replace(":", "").replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().replace(" ", "_");
            } else if (!line.isEmpty() && (line.startsWith("*") || line.startsWith("-"))) {
                items.add(line.replaceAll("^[\\-*\\s]+", "").replaceAll("\\*\\*", "").trim());
            }
        }
        if (!items.isEmpty()) {
            structuredResources.put(currentSection, items);
        }
        return structuredResources;
    }

    private List<String> extractBulletPoints(String section) {
        List<String> list = new ArrayList<>();
        String[] lines = section.split("\\n");
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("*") || line.startsWith("-")) {
                // Remove *, **, -, etc.
                list.add(line.replaceAll("^[\\-*\\s]+", "").replaceAll("\\*\\*", "").trim());
            }
        }
        return list;
    }    

}
