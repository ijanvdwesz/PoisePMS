# Poise Project Management System (PMS)

## Overview

The Poise Project Management System (PMS) is a Java application designed to manage various aspects of project management workflow. It connects to a MySQL database to handle operations related to architects, customers, contractors, and projects.

## Features

- **Architect Management**: Add, update, delete, and display architects.
- **Customer Management**: Add, update, delete, and display customers.
- **Contractor Management**: Add, update, delete, and display contractors.
- **Project Management**: Add, update, delete, display, and finalize projects.
- **Database Integration**: Connects to a MySQL database to perform CRUD operations.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 21 or higher
- MySQL Server 8.0 or higher
### Under Development
- Docker (for containerization)
- Docker Compose (for managing multi-container Docker applications, under development)

### Running the Application Locally

1. **Set Up the Database**

   -A dump file for the database is provided to set up the initial data. Import this dump file into your MySQL instance to populate the database.

### Run the Application Using Eclipse

1. **Import the Project**

   - Open Eclipse.
   - Go to `File` > `Import`.
   - Choose `Existing Projects into Workspace` and click `Next`.
   - Browse to the directory containing PoisePMS and click `Finish`.

2. **Run the Application**

   - Locate the `PoisePMS` class in the `Package Explorer`.
   - Right-click on the `PoisePMS` class.
   - Select `Run As` > `Java Application`.

## Docker and Containerization

The Docker setup for this project is currently under development. Once finalized, it will include:

- Building a Docker image for the application.
- Setting up Docker Compose to manage multi-container applications, including MySQL.

## GUI Development

A graphical user interface (GUI) is in progress. The GUI will enhance user interaction with the application.

## Note 
Please ignore the files not required by this task such as Docker,MANIFEST,compose and all GUI files commented out ,they are still in the development process 