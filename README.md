# Smart Tasker - Backend API

Smart Tasker is a full-featured task management backend built using **Spring Boot** following the **Hexagonal Architecture**. It supports JWT-based authentication, PostgreSQL persistence, email notifications, and RESTful APIs to manage tasks seamlessly.

## 🔧 Tech Stack

- **Java 17**
- **Spring Boot 3.2.2**
- **Hexagonal Architecture**
- **Spring Security + JWT**
- **PostgreSQL**
- **Hibernate + JPA**
- **Lombok**
- **JavaMailSender (Email Integration)**
- **Maven**

---

## 🚀 Features

- ✅ User Registration & Login (JWT Authentication)
- ✅ Create, Update, Delete Tasks
- ✅ Mark Tasks as Completed
- ✅ View All Tasks / Filter Completed Tasks
- ✅ Send Welcome Email on Signup
- ✅ Role-based Authentication (User/Admin ready)
- ✅ Exception Handling with Standard Error Responses

---

## 🧱 Project Structure (Hexagonal)

```
smart-tasker/
├── domain/              # Core Business Logic
│   └── model/
│   └── service/
│
├── application/         # Application Services (Use Cases)
│   └── ports/
│
├── infrastructure/      # External Concerns (DB, Mail, JWT, etc.)
│   └── persistence/
│   └── security/
│   └── config/
│   └── mail/
│
├── adapters/            # REST Controllers (API Layer)
│   └── in/
│   └── out/
│
└── SmartTaskerApplication.java
```

---

## 🔐 Authentication Flow

1. Users register with their email and password.
2. JWT is issued on login and must be passed in the `Authorization` header for protected endpoints.
3. Passwords are encrypted using **BCrypt**.

Example header:
```
Authorization: Bearer <token>
```

---

## 📬 Email Integration

- A welcome email is sent to users upon successful registration.
- You can configure SMTP settings in `application.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

## 🧪 Running Locally

### 1. Clone the repo

```bash
git clone https://github.com/KumarPadigeri/smart-tasker.git 
cd smart-tasker
```

### 2. Configure PostgreSQL

Create a database named `smart_tasker`.

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/smart_tasker
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The app runs at: `http://localhost:8080`

---

## 🧪 API Endpoints

| Method | Endpoint              | Description               |
|--------|-----------------------|---------------------------|
| POST   | /api/auth/register    | Register new user         |
| POST   | /api/auth/login       | Login and get JWT         |
| GET    | /api/tasks            | Get all tasks             |
| POST   | /api/tasks            | Create a new task         |
| PUT    | /api/tasks/{id}       | Update existing task      |
| DELETE | /api/tasks/{id}       | Delete task by ID         |
| PUT    | /api/tasks/{id}/done  | Mark task as completed    |

---

## 🧹 Future Enhancements

- 🗂️ Task categories & priorities
- 🔔 Email reminders for due tasks
- 📊 Admin dashboard analytics
- 📱 WebSocket-based real-time updates

---

## ✨ Author

**Kumara Swamy Padigeri**  
📫 [pk24@usf.edu](mailto:pk24@usf.edu)  
🔗 [LinkedIn](https://www.linkedin.com/in/kumar-padigeri)  
🌐 [Portfolio](https://kumarpadigeri.wixsite.com/web-developer) (Replace with actual link)
