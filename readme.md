markdown

# 3TierRuleEngine-with-AST

## Overview

The 3TierRuleEngine-with-AST is a rule engine designed with a three-tier architecture using Abstract Syntax Trees (AST).
This project is intended to provide a robust and flexible framework for building and evaluating rule-based logic in a
structured and maintainable manner.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Design Choices](#design-choices)
- [Directory Structure](#directory-structure)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before you begin, ensure you have the following installed:

- Docker
- Docker Compose
- Java Development Kit (JDK) 11 or higher
- Maven

## Installation

### Clone the Repository

sh
git clone https://github.com/soam1/3TierRuleEngine-with-AST.git
cd 3TierRuleEngine-with-AST

### Setup Docker Containers

This project uses Docker for containerization. Ensure Docker is installed and running on your machine.

1. Build the Docker images:
   sh
   docker-compose build


2. Start the containers:
   sh
   docker-compose up -d

### Install Dependencies

Install the required Java dependencies using Maven:
sh
./mvnw clean install

## Running the Application

### Using Docker

Once the containers are up, you can access the application via:

- Web server: `http://localhost:8080`
- Database: `jdbc:mysql://localhost:3306/rule_engine`

### Without Docker

To run the application locally without Docker:

1. Ensure the database server is running and accessible.
2. Update the `application.properties` file with your database configuration.
3. Start the application using Maven:
   sh
   ./mvnw spring-boot:run

## Design Choices

### Three-Tier Architecture

The application is structured into three main layers:

1. **Presentation Layer**: Handles the UI/UX and user interactions.
2. **Business Logic Layer**: Contains the core logic of the rule engine, processing ASTs.
3. **Data Access Layer**: Manages data storage, retrieval, and database interactions.

### Abstract Syntax Tree (AST)

AST is used to represent the structure of rules in a tree form, allowing efficient parsing, analysis, and transformation
of rule logic.

### Containerization

Docker is used to ensure a consistent and reproducible environment, facilitating easier deployment and scalability.

## Directory Structure

The project structure is as follows:

3TierRuleEngine-with-AST/
├── .mvn/ # Maven wrapper files
├── src/ # Source code
│ ├── main/
│ │ ├── java/ # Java source files
│ │ │ ├── com/akashsoam/RuleEngine/
│ │ │ │ ├── RuleEngineApplication.java
│ │ │ │ ├── controller/
│ │ │ │ │ └── RuleController.java
│ │ │ │ ├── model/ # Model classes
│ │ │ │ └── service/ # Service classes
│ │ └── resources/ # Application resources
│ │ └── application.properties
│ └── test/ # Test source files
├── .gitignore # Git ignore file
├── mvnw # Maven wrapper script
├── mvnw.cmd # Maven wrapper script for Windows
├── pom.xml # Maven project file
└── README.md # Project README

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request for review.