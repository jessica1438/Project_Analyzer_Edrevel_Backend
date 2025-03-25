The backend is built using Spring Boot in IntelliJ IDEA. It exposes a REST API that accepts a project scenario and a list of constraints from the frontend, then leverages a Large Language Model (LLM) (via Spring AI and OpenAI) to generate a structured response.

🔧 Key Features:

Built with Java + Spring Boot
Exposes a POST endpoint at /scenario/analyze
Uses Spring AI's ChatModel + OpenAI to generate content
Returns:
Scenario Summary
Potential Pitfalls
Proposed Strategies
Recommended Resources
Disclaimer

Tools & Libraries:

Spring Boot
Spring Web
Spring AI (OpenAI integration)
IntelliJ IDEA for development

Frontend Screenshots: 

<img width="882" alt="Screenshot 2025-03-25 at 5 47 43 PM" src="https://github.com/user-attachments/assets/38d5a191-8743-4f13-bd84-7df5f0c9c5ee" />
<img width="953" alt="Screenshot 2025-03-25 at 5 39 15 PM" src="https://github.com/user-attachments/assets/92421f6f-3897-4f3b-afed-c5c7816b0a7c" />
<img width="912" alt="Screenshot 2025-03-25 at 5 39 00 PM" src="https://github.com/user-attachments/assets/fb6cf76f-b2f5-438b-ab16-da416bd7fbeb" />
