package com.ai.SpringAiDemo;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;



import java.util.Map;
import java.util.List;

@Service
public class AiService {

    private final ChatModel chatModel;

    public AiService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public ScenarioAnalysisResponse generateAnalysis(ScenarioAnalysisRequest request) {
        String scenario = request.getScenario();
        String constraints = String.join(", ", request.getConstraints());

        String template = """
                Given the following scenario and constraints, generate:
                1. A brief summary of the scenario (1-2 sentences)
                2. A list of potential pitfalls
                3. A list of proposed strategies
                4. A list of recommended resources
                5. A one-sentence disclaimer about AI limitations or the need for expert consultation

                Scenario: {scenario}
                Constraints: {constraints}
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "scenario", scenario,
                "constraints", constraints
        );

        Prompt prompt = promptTemplate.create(params);
        String aiResponse = chatModel.call(prompt).getResult().getOutput().getText();

        return parseAiResponse(aiResponse);
    }

    private ScenarioAnalysisResponse parseAiResponse(String responseText) {
        // Simple parser (can be replaced with more robust parsing or JSON-based AI responses)
        ScenarioAnalysisResponse response = new ScenarioAnalysisResponse();

        String[] parts = responseText.split("(?=\\d\\.\\s)");

        for (String part : parts) {
            if (part.startsWith("1.")) {
                response.setScenarioSummary(part.replaceFirst("1\\.\\s.*?:?\\s*", "").trim());
            } else if (part.startsWith("2.")) {
                response.setPotentialPitfalls(extractBulletList(part));
            } else if (part.startsWith("3.")) {
                response.setProposedStrategies(extractBulletList(part));
            } else if (part.startsWith("4.")) {
                response.setRecommendedResources(extractBulletList(part));
            } else if (part.startsWith("5.")) {
                response.setDisclaimer(part.replaceFirst("5\\.\\s.*?:?\\s*", "").trim());
            }
        }

        return response;
    }

    private List<String> extractBulletList(String section) {
        return section.lines()
                .skip(1) // Skip the heading line
                .map(String::trim)
                .filter(line -> !line.isEmpty() && (line.startsWith("-") || line.startsWith("*") || line.matches("^\\d+\\..*")))
                .map(line -> line.replaceFirst("^[-*\\d.\\s]+", "").trim())
                .toList();
    }
}
