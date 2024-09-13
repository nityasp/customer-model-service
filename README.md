**Project Overview**
--------------------------------------------------------------------------------------------------------------------------------------------------------
This project is a Spring Boot application for managing customer data with PostgreSQL as the database. The application is containerized using Docker and can be deployed using Docker Compose or Helm for Kubernetes. It includes logging capabilities with sensitive data protection and integrates Prometheus for monitoring and telemetry.

**Prerequisites**
--------------------------------------------------------------------------------------------------------------------------------------------------------
Before setting up and running the application, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
- **Maven** (for building the project)
- **Docker** and **Docker Compose** (for containerization)
- **MiniKube** (for deploying with Helm)
- **Helm** (for managing Kubernetes deployments)

**Setting Up the Project**
---------------------------------------------------------------------------------------------------------------------------------------------------------
**1. Clone the Repository**

  git clone https://github.com/nityasp/customer-model-service.git

  cd customer-model

**2. Build the Project**

Use Maven to build the Spring Boot application:

_mvn clean install_

This command generates the application’s JAR file in the target/ directory.

**Running the Application Locally**
-----------------------------------------------------------------------------------------------------------------------------------------------------------
**1. Running with Docker Compose**

The application can be run using Docker Compose, which will start the PostgreSQL database, the Spring Boot application, and Prometheus for monitoring.

_docker-compose up --build_

- **Spring Boot Application**: Runs on http://localhost:8089
- **Prometheus**: Accessible at http://localhost:9090
- **PostgreSQL**: Accessible on port 5432 (from within Docker)

**2. Accessing the Application**

- **API Endpoints**: Below are the APIs that are exposed 

  **Get all customer details**: 

  curl -X GET http://localhost:8089/getCustomers

  **Get customer details by id:** 

  curl -X GET http://localhost:8089/id/{id}

  **Create new customer:** 

  curl -X POST http://localhost:8089 -H 'Content-Type:application/json' -d '{"prefix":"Mr.","firstName":"Jennifer","middleName":"C.","lastName":"Yu","suffix":"Jr.","email":"jennifer.yu@sample.com","phoneNumber":"123-583-6875"}'

  **Update customer details:**

  curl -X PUT http://localhost:8089/{id} -H 'Content-Type:application/json' -d '{" email":"jen.yu@sample.com"}'

  **Delete customer:**

  curl -X DELETE http://localhost:8089/{id}

- **Prometheus Metrics**: Metrics are available at http://localhost:9090/actuator/prometheus

**3. Stopping the Application**

To stop the services, run: _docker-compose down_

**Deploying to Kubernetes with Helm**
--------------------------------------------------------------------------------------------------------------------------------------------------------------
1. **Start minikube** 

   _minikube start --driver=docker_

2. **Build docker image in minikube** 

    _docker build -t customer-model:latest ._

    _docker build –t Prometheus:latest ._

   To see the images created in minikube: _minikube image ls_

3. **Package and Deploy with Helm**

   To deploy the application on a Kubernetes cluster using Helm, follow these steps:

   _helm install mychart customer-model-chart_

4. **Accessing the Services**

   Once deployed, the services will be available on your Kubernetes cluster's IP. Use   _kubectl get services_ to retrieve the external IP and ports.

5. **Connect to database to add data:**

   To find the podname of the postgres container: _kubectl get pods_

   To connect to the postgres database in the terminal: 

   _kubectl exec –it <POSTGRES\_POD\_NAME> -- psql –d <DB\_NAME> -U <DB\_USER\_NAME>_

   In the shell script, create a new table and insert data in the table as shown in the schema.sql file in the project directory.

6. **To access the REST APIs in POSTMAN** 

   Open a new Terminal, and run command _minikube tunnel_

7. **Uninstalling the Helm Release**

   To uninstall the Helm release: _helm uninstall mychart_

8. **To stop Minikube**

   _minikube stop_

**Monitoring and Logging**
--------------------------------------------------------------------------------------------------------------------------------------------------------------
**1. Prometheus Monitoring**

Prometheus is configured to scrape metrics from the Spring Boot application. Access Prometheus UI at http://localhost:9090 to view the metrics.

**2. Logging Configuration**

Logs are managed by SLF4J with Logback. The logging pattern and level can be customized in the logback-spring.xml file located in src/main/resources.

- **INFO Level**: Logs all major operations and interactions with the database.
- **ERROR Level**: Logs any exceptions or errors that occur.

Sensitive information, such as email addresses, is masked in logs to protect user privacy.

**Troubleshooting**
----------------------------------------------------------------------------------------------------------------------------------------------------------------
**1. Common Issues**

- **Docker Compose Issues**: Ensure Docker is running and the necessary ports (8089, 5432, 9090) are not in use by other services.
- **Kubernetes Deployment**: If the application is not starting correctly, check the logs using _kubectl logs <pod-name>_.

**2. Log Levels**

If you encounter issues, increase the log level in logback-spring.xml to DEBUG for more detailed output.

**Conclusion**
------------------------------------------------------------------------------------------------------------------------------------------------------------------
This project provides a complete solution for managing customer data using a Spring Boot application with PostgreSQL, containerized with Docker, and deployed with Helm. It includes comprehensive logging and monitoring capabilities, making it suitable for production environments.

