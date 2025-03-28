# Trackerciser (IN PROGRESS...)

Try it out here: [www.trackerciser.com](https://www.trackerciser.com)

**Note:** If the link does not work, the project might be turned off to save resources on AWS or is currently under development.

---

## About
Trackerciser is a personal project aimed at helping people track their workout progress efficiently. This tool allows users to log their workout details and visualize progress using line charts. It is particularly useful for tracking routines over time, eliminating guesswork, and encouraging consistent improvement.

**Key Benefits:**
- Log workout details including weights, reps, sets, duration and rest.
- Track progress visually using line charts.
- Save time and maintain consistency in workout routines.
- Stay motivated by pushing yourself to improve based on previous sessions.

---

## Technology Stack
### **Backend**
- **Language:** Java (Spring Boot)
- **Java Version:** 21 (should work on 16+)
- **Database:** MySQL (for production), H2 (for testing)

### **Frontend**
- **Framework:** React

---

## How to Run the Project
### **Backend**
The project can be built and run using Maven or the provided Maven wrapper (`mvnw`).

#### **Configuration Steps:**
1. **Database Configuration:**
    - The `.properties` files are not included in the repository for security reasons.
    - You need to add `application.properties` with the following:
        - MySQL connection details (URL, username, password).
        - Table behavior settings (`create`, `create-drop`, `update`, `validate`, etc.).
        - Data initialization (`data.sql`) if required.

2. **Testing Configuration:**
    - Tests use the `test` profile.
    - You need to create `application-test.properties` to configure the H2 database.
    - You can modify the test database settings to fit your needs.

3. **Authentication Configuration:**
    - In `JwtAuthenticationResource.java`:
        - The project is hosted on AWS and uses HTTP-only cookies with HTTPS.
        - If running locally, modify/remove the following settings:
            - `.domain` (remove or change as needed)
            - `.httpOnly`, `.secure`, `.sameSite` (ensure they match your setup)

4. **Security & CORS Configuration:**
    - In `JwtSecurityConfig.java`:
        - Modify CORS mappings, methods, and allowed endpoints according to your setup.

### **Frontend**
- Update the `const HTTP` value in:
    - [`./project/frontend/trackerciser-app/src/api/index.js`](./project/frontend/trackerciser-app/src/api/index.js)
- Ensure it matches your backend URL and CORS configuration.

---

### **Contributing**
This project is a work in progress. Contributions, suggestions, and feedback are welcome!

---


