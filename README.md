# QuoteAI

QuoteAI leverages the capabilities of artificial intelligence to generate quotes.

To use QuoteAI, simply visit the website https://petronealessio.com and start generating quotes.

The project was conceived with the aim of showcasing my skills and knowledge.
If you want to know more about me, you will find several links at the bottom of the website.

If you are interested in the technical aspects of the project, you will find further details below.

## Technology Stack

### Programming Language, Framework and Tools
QuoteAI has been developed using the Java programming language, with the support of the
Spring Boot framework to ensure flexibility and maintainability in the application.
Throughout the development process, IntelliJ IDEA was employed as the Integrated Development Environment (IDE).
Additionally, Maven was used as a dependency management tool to simplify the build process and project management.

### RESTful
To handle requests, the project extensively utilizes the Representational State Transfer (REST) protocol,
enabling efficient and scalable communication between client and server.

### Artificial Intelligence
For the artificial intelligence component, QuoteAI harnesses the power of GPT (Generative Pre-trained Transformer)
technology developed by OpenAI. GPT enables the generation of human-like text based on a given prompt,
making it ideal for creating quotes.

### Database
The generated quotes are stored in a database, utilizing MySQL as the Database Management System (DBMS).
Considering that the project was developed solely for demonstrative purposes, the system is designed to store only
the latest 10 quotes for each language. A management logic has been implemented to automatically remove older quotes
if the number exceeds the limit of 10. This approach has been adopted to prevent excessive memory usage, considering
that the server hosting the application has resource limitations.

### Deployment and Server
For deploying the QuoteAI project, Docker has been adopted as the primary tool to ensure a consistent and isolated
execution environment. This choice enables simplified management of dependencies and configurations, easing the
deployment process across different platforms. The project has indeed been hosted on an Amazon Web Services (AWS) EC2
instance, providing reliable flexibility and scalability.

### Disclaimer
Please note that attempting to execute this code may not yield the expected results, as some sensitive data such as
the OpenAI API key and database access credentials have been omitted for security reasons.