# Travel Planner

## Overview

Travel Planner is a web application built with Spring Boot that allows users to plan their trips efficiently. Users can create trips, manage itineraries, set budgets, and maintain packing lists. The application provides a user-friendly interface using Thymeleaf and Bootstrap for a responsive design.

## Features

- **Trip Management**: Create, edit, and delete trips.
- **Itinerary Planning**: Add activities with dates and times for each trip.
- **Budget Tracking**: Set and manage budgets for each trip, including expenses.
- **Packing List**: Create and manage packing lists for trips.
- **User Notifications**: Display success and error messages for user actions.

## Technologies used
  
- **Backend**: Java, Spring Boot
- **Frontend**: HTML, Thymeleaf, Bootstrap
- **Database**: MySQL
- **Build Tool**: Maven

## Getting started

### Prerequisites
- Java 17 or higher
- Maven
- MySQL Server

### Installation
1. Clone the repository
```bash
git clone https://github.com/yourusername/travelplanner.git
cd travelplanner
```
2. Set up the database
   - Create a new database in MySQL (e.g., `travelplanner`).
   - Update the application.properties file in src/main/resources with your database credentials:
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/travelplanner
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```
3. Build the project
   `
   ./mvnw clean install
   `
4. Run the application
   `
   ./mvnw spring-boot:run
   `
5. Access the application at `http://localhost:8080`

## Usage

- **Create a Trip**: Navigate to the "Create Trip" page to add a new trip.
- **Manage Itinerary**: After creating a trip, you can add activities to your itinerary.
- **Set Budget**: Define a budget for your trip and track expenses.
- **Packing List**: Create a packing list to ensure you don't forget any essentials.

## Project report
[Project report](https://drive.google.com/file/d/12R7J-Q1O4KjsKe57HiYQFpzLMMQX5cSd/view?usp=sharing)
