**Project Overview**
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
This project is a Spring Boot application for managing customer data with PostgreSQL as the database. The application is containerized using Docker and can be deployed using Docker Compose or Helm for Kubernetes. It includes logging capabilities with sensitive data protection and integrates Prometheus for monitoring and telemetry.

**Prerequisites**
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Before setting up and running the application, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
- **Maven** (for building the project)
- **Docker** and **Docker Compose** (for containerization)
- **Kubernetes** (optional, for deploying with Helm)
- **Helm** (optional, for managing Kubernetes deployments)

**Setting Up the Project**
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**1. Clone the Repository**

_git clone https://github.com/nityasp/customer-model-service.git_

_cd customer-model_

**2. Build the Project**

Use Maven to build the Spring Boot application:

_mvn clean install_

This command generates the application’s JAR file in the target/ directory.

**Running the Application Locally**
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**1. Running with Docker Compose**

The application can be run using Docker Compose, which will start the PostgreSQL database, the Spring Boot application, and Prometheus for monitoring.

_docker-compose up --build_

- **Spring Boot Application**: Runs on _http://localhost:8089_
- **Prometheus**: Accessible at _http://localhost:9090_
- **PostgreSQL**: Accessible on port 5432 (from within Docker)

**2. Accessing the Application**

- **API Endpoints**: The API is exposed at _http://localhost:8089/_
- **Prometheus Metrics**: Metrics are available at _http://localhost:8089/actuator/prometheus_

**3. Stopping the Application**

To stop the services, run:

_docker-compose down_

**Deploying to Kubernetes with Helm**
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**1. Package and Deploy with Helm**

If you're deploying the application on a Kubernetes cluster using Helm, follow these steps:

_helm install mychart customer-model-chart_

**2. Accessing the Services**

Once deployed, the services will be available on your Kubernetes cluster's IP. Use kubectl get services to retrieve the external IP and ports.

**3. Uninstalling the Helm Release**

To uninstall the Helm release:

_helm uninstall customer-model-chart_

**Monitoring and Logging**
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**1. Prometheus Monitoring**

Prometheus is configured to scrape metrics from the Spring Boot application. Access Prometheus UI at _http://localhost:9090_ to view the metrics.

**2. Logging Configuration**

Logs are managed by SLF4J with Logback. The logging pattern and level can be customized in the logback-spring.xml file located in src/main/resources.

- **INFO Level**: Logs all major operations and interactions with the database.
- **ERROR Level**: Logs any exceptions or errors that occur.

Sensitive information, such as email addresses, is masked in logs to protect user privacy.

**Troubleshooting**
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**1. Common Issues**

- **Docker Compose Issues**: Ensure Docker is running and the necessary ports (8080, 5432, 9090) are not in use by other services.
- **Kubernetes Deployment**: If the application is not starting correctly, check the logs using _kubectl logs <pod-name>_.

**2. Log Levels**

If you encounter issues, increase the log level in logback-spring.xml to DEBUG for more detailed output.

**Conclusion**
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
This project provides a complete solution for managing customer data using a Spring Boot application with PostgreSQL, containerized with Docker, and deployed with Helm. It includes comprehensive logging and monitoring capabilities, making it suitable for production environments.

