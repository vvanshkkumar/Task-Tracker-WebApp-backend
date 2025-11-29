# Task Tracker Web App — Backend (Spring Boot + PostgreSQL)

This repository contains the **backend** of a Task Tracking web application built using  
**Spring Boot, PostgreSQL, REST APIs, DTOs, layered architecture, mappers, validation**, and  
a global exception-handling mechanism.

> **Note:**  
> The React + Vite **frontend was auto-generated from a template** and **not written by me**.  
> My focus for this project was purely on designing and building a clean, scalable backend.

---

## Features (Backend)

### Task List Management
- Create, update, delete task lists  
- Fetch all task lists  
- Get task list by ID  
- DTO-based request/response handling  
- Validation & custom exception responses  

### Task Management
- Create tasks under a task list  
- Update task metadata  
- Delete tasks  
- Fetch tasks inside a task list  
- Fetch a single task by ID  
- Handles priority, status, timestamps, due date, and relationships  

### Architecture Highlights
- **Controller → Service → Repository** clean layered flow  
- **DTO ↔ Entity mapping** using custom mapper classes  
- **GlobalExceptionHandler** for unified API error responses  
- **Records & immutable DTOs** for cleaner code  
- **PostgreSQL with Docker Compose** for database environment  
- **JPA + Hibernate** for ORM and query handling  

---

## **Tech Stack**

### Backend
- **Java 21**
- **Spring Boot 4**
- **Spring Data JPA**
- **PostgreSQL**
- **Hibernate**
- **Docker Compose**
- **Maven**

### Frontend (template)
- **React + TypeScript + Vite + TailwindCSS**
- *Frontend auto-generated from a template, not written by me.*

---

## Project Structure

```
Backend/
 └── src/main/java/com/vvanshkkumar/tasks
       ├── Controllers/            # REST controllers
       ├── Service/                # Business logic layer
       ├── Repositories/           # JPA repositories
       ├── Mappers/                # DTO <-> Entity converters
       ├── domain/
       │    ├── entities/          # JPA Entities
       │    └── Dto/               # Request/response models
       └── GlobalExceptionHandler  # API error handling

Frontend/
 └── (Template-generated React code)
```

---

## Running the Backend

### 1. Start PostgreSQL using Docker
```
docker compose up -d
```

### 2. Run Spring Boot Application
You can run it:

- from IntelliJ using the green run button  
- or using Maven

```
mvn spring-boot:run
```

Backend runs at:

```
http://localhost:8080/api/task-lists
http://localhost:8080/api/task-lists/{id}/tasks
```

---

## 3. Endpoints Overview

### TaskLists
```
GET    /api/task-lists
POST   /api/task-lists
GET    /api/task-lists/{id}
PUT    /api/task-lists/{id}
DELETE /api/task-lists/{id}
```

### Tasks
```
GET    /api/task-lists/{taskListId}/tasks
POST   /api/task-lists/{taskListId}/tasks
GET    /api/task-lists/{taskListId}/tasks/{taskId}
PUT    /api/task-lists/{taskListId}/tasks/{taskId}
DELETE /api/task-lists/{taskListId}/tasks/{taskId}
```

---

## What I Learned
- Building a **real-world REST API** using Spring Boot  
- Designing **DTO/Entity separation**  
- Implementing **validation + custom exceptions**  
- Structuring projects using **clean architecture**  
- Managing database relations (One-to-Many)  
- Implementing **end-to-end CRUD backend systems**

---

## About the Author
Built by **Vansh Kumar**, B.Tech CSE student.  
Actively learning Backend Development, Java, Spring Boot, and System Design.

---

## Show Support
If you like this project, give it a ⭐ on GitHub!

