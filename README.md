# 🌐 Mini Social Network App

A **miniature social media platform** built with **Spring Boot**, demonstrating core features such as user registration, post creation, comments, likes, and access control.

> Designed for learning and prototyping purposes.

---

##  Table of Contents:

- [ Features](#-features)  
- [ Tech Stack](#-tech-stack)  
- [ Database Schema](#-database-schema)  
- [ API Endpoints](#-api-endpoints)  
- [ Setup & Run](#-setup--run)  
- [ Validation Rules](#-validation-rules)  
- [ Swagger UI](#-swagger-ui)

---

## Features

### 👥 User Management
- Register new users  
- Get user by ID  
- List all users  

### 📝 Post Management
- Create, update, and delete own posts  
- View all posts with comments & likes  
- View posts by specific users  
- View individual posts with full details  
- Cannot modify others' posts  

### 💬 Comment Management
- Comment on own posts  
- Edit/delete own comments  
- Delete other users' comments on own posts  
- Cannot edit others' comments  

### 👍 Like Management
- Like or unlike posts (only once per post)  
- Users can ignore/react/unreact freely  

---

## 🧰 Tech Stack

| Component       | Technology          |
|----------------|---------------------|
| Language        | Java 17             |
| Framework       | Spring Boot         |
| ORM             | Hibernate (via JPA) |
| Database        | PostgreSQL          |
| Build Tool      | Maven               |
| API Docs        | Swagger / OpenAPI   |
| API Testing     | Postman/PostAPI     |

---

## 🗂️ Database Schema
![socialnetDB](https://github.com/user-attachments/assets/98d97ea0-b5ca-4f1b-af0b-7404befdff89)
### 🧑‍💼 `users`
| Field         | Type     | Constraints         |
|---------------|----------|---------------------|
| id            | UUID     | Primary Key         |
| first_name    | String   | Not Null            |
| last_name     | String   | Not Null            |
| username      | String   | Unique, Not Null    |
| email         | String   | Unique, Not Null    |
| mobile        | String   | Unique              |
| birth_date    | Date     | Optional            |

### 📝 `posts`
| Field         | Type     | Description         |
|---------------|----------|---------------------|
| id            | UUID     | Primary Key         |
| post_text     | Text     | Not Null            |
| created_at    | Timestamp | Default now()      |
| updated_at    | Timestamp | Nullable           |
| user_id       | UUID     | FK → users.id       |

### 💬 `comments`
| Field         | Type     | Description         |
|---------------|----------|---------------------|
| id            | UUID     | Primary Key         |
| comment_text  | Text     | Not Null            |
| created_at    | Timestamp | Default now()      |
| updated_at    | Timestamp | Nullable           |
| user_id       | UUID     | FK → users.id       |
| post_id       | UUID     | FK → posts.id       |

### ❤️ `post_likes`
| Field         | Type     | Description         |
|---------------|----------|---------------------|
| id            | UUID     | Primary Key         |
| user_id       | UUID     | FK → users.id       |
| post_id       | UUID     | FK → posts.id       |

> ☑️ Unique Constraint on `(user_id, post_id)` to prevent duplicate likes.

---

## 📡 API Endpoints
<img width="1000" alt="API1" src="https://github.com/user-attachments/assets/08c1087a-2203-4388-84c5-da208386e860" /> <img width="1000" alt="API2" src="https://github.com/user-attachments/assets/ea4664bb-ef21-439c-9ef0-c4fe3e634deb" />

### 👥 User Endpoints `/api/users`

| Method | Endpoint            | Description             |
|--------|---------------------|-------------------------|
| POST   | `/api/users`        | Register a new user     |
| GET    | `/api/users/{id}`   | Get user by ID          |
| GET    | `/api/users`        | Get all users           |

### 📝 Post Endpoints `/api/posts`

| Method | Endpoint                    | Description                     |
|--------|-----------------------------|---------------------------------|
| POST   | `/api/posts`                | Create new post                 |
| PUT    | `/api/posts/{postId}`       | Update own post                 |
| DELETE | `/api/posts/{postId}`       | Delete own post                 |
| GET    | `/api/posts`                | View all posts                  |
| GET    | `/api/posts/{postId}`       | View specific post              |
| GET    | `/api/posts/user/{userId}`  | View all posts by a user        |

> 🛡 Requires `X-User-Id` header for authorization on PUT/DELETE

### 💬 Comment Endpoints `/api/comments`

| Method | Endpoint                      | Description                     |
|--------|-------------------------------|---------------------------------|
| POST   | `/api/comments`               | Create a new comment            |
| PUT    | `/api/comments/{commentId}`   | Edit own comment                |
| DELETE | `/api/comments/{commentId}`   | Delete own or others' comment*  |

> 🛡 `X-User-Id` required.  
> \* Can delete others’ comments on own posts.

### 👍 Like Endpoints `/api/posts/{postId}`

| Method | Endpoint                    | Description             |
|--------|-----------------------------|-------------------------|
| POST   | `/like`                     | Like a post             |
| DELETE | `/unlike`                   | Unlike a post           |

> 🔁 `LikeRequest` body with `userId` is required for both.

---

## ⚙️ Setup & Run

### 🔧 Prerequisites

- Java 17+  
- Maven  
- PostgreSQL  

### 🗃️ Database Configuration

1. Create a PostgreSQL database named `soocial_network`
2. Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/soocial_network
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=create
