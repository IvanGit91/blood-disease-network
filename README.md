# Blood Diseases Network (BDN) - Full-Stack Java AngularJS Application

> ⚠️ **Legacy Project Notice**: This project was developed in 2018 using Java 1.8 and AngularJS 1.4.9, making it a
> legacy application with older technology stack.

## Project Overview

Blood Diseases Network (BDN) is a web-based management platform designed for healthcare professionals working in
hematology. The application provides a comprehensive system for managing patient medical records, treatments, and
diagnostic information for various blood diseases including:

- **Chronic Lymphocytic Leukemia (CLL)**
- **Multiple Myeloma**
- **Lymphomas**
- **Myelodysplastic Syndromes (MDS)**
- **Myeloproliferative Disorders**

### Key Features

- **User Management**: Role-based access control with ADMIN and USER roles
- **Hospital Management**: Multi-hospital support with centralized administration
- **Medical Records**: Comprehensive patient data management with disease-specific forms
- **Therapy Tracking**: Treatment line management and therapy response monitoring
- **Dashboard Analytics**: Basic statistical views and data visualization
- **Session Management**: Secure authentication with session timeout protection

### Technology Stack

**Backend:**

- Java 1.8
- Spring Boot 2.0.4
- Spring Security
- Spring Data JPA
- PostgreSQL
- Maven 3.x

**Frontend:**

- AngularJS 1.4.9
- Bootstrap 3.3.6
- jQuery 3.3.1
- Chart.js for data visualization

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java Development Kit (JDK) 1.8** or higher
- **Maven 3.6+** for dependency management and building
- **PostgreSQL 9.6+** database server
- **Git** for version control (optional)

## Database Setup

1. **Install PostgreSQL** on your system if not already installed
2. **Create a new database** for the application:
   ```sql
   CREATE DATABASE blood_diseases_network;
   CREATE USER bdn_user WITH PASSWORD 'your_password';
   GRANT ALL PRIVILEGES ON DATABASE blood_diseases_network TO bdn_user;
   CREATE SCHEMA bdn_db;
   ALTER SCHEMA bdn_db owner TO bdn_user;
   ```

3. **Configure database connection** by creating `application-dev.properties` in `src/main/resources/`:
   ```properties
   # Database Configuration
   spring.datasource.url=jdbc:postgresql://localhost:5432/blood_diseases_network
   spring.datasource.username=bdn_user
   spring.datasource.password=your_password
   spring.datasource.driver-class-name=org.postgresql.Driver
   
   # JPA Configuration
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL95Dialect
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   
   # Logging
   logging.level.it.ivan.bdn=DEBUG
   logging.file.name=log/spring.log
   ```

## Building and Running

### 1. Clone the Repository

```bash
git clone <repository-url>
cd blood-disease-network
```

### 2. Build the Application

```bash
# Clean and compile the project
./mvnw clean compile

# Run tests (if any)
./mvnw test

# Package the application
./mvnw clean package
```

### 3. Run the Application

**Option A: Using Maven Spring Boot Plugin**

```bash
# Run with development profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

**Option B: Using Java JAR**

```bash
# After packaging, run the JAR file
java -jar target/BDN_0.0.1.jar --spring.profiles.active=dev
```

### 4. Access the Application

Once the application is running, access it at:

- **URL**: `http://localhost:8083/bdn`
- **Default Port**: 8083
- **Context Path**: `/bdn`

### 5. Initial Setup

On first run, the application will:

- Automatically create database tables (DDL auto-create)
- Import the initial data from the `data.sql` file

### 6. Preloaded users

The application can be accessed with the following preloaded users:

- **Admin**: admin / admin
- **User**: user / user

## Project Structure

```
blood-disease-network/
├── src/
│   ├── main/
│   │   ├── java/it/ivan/bdn/
│   │   │   ├── BloodDiseasesNetworkApplication.java
│   │   │   ├── config/                    # Configuration classes
│   │   │   ├── controller/                # REST controllers
│   │   │   ├── model/                     # JPA entities
│   │   │   ├── repository/                # Data repositories
│   │   │   ├── security/                  # Security configuration
│   │   │   └── exception/                 # Custom exceptions
│   │   └── resources/
│   │       ├── static/                    # Frontend assets
│   │       │   ├── js/                    # AngularJS application
│   │       │   ├── css/                   # Stylesheets
│   │       │   ├── template/              # HTML templates
│   │       │   └── index.html             # Main HTML file
│   │       └── application.properties     # Configuration
│   └── test/                              # Test classes
├── target/                                # Build output
├── log/                                   # Application logs
├── pom.xml                               # Maven configuration
└── README.md                             # This file
```

## Configuration

### Application Properties

The main configuration is in `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8083
server.servlet.context-path=/bdn
server.servlet.session.cookie.name=BDNSESSIONID
# Static Resources
spring.resources.static-locations=classpath:/static/
```

### Profiles

- **Default Profile**: Basic configuration
- **Development Profile**: Extended logging, database auto-creation
- **Production Profile**: Optimized for production deployment (needs to be configured)

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Known Issues and Limitations

- **Legacy Technology Stack**: Built with Java 1.8 and AngularJS 1.4.9
- **Incomplete Implementation**: Several features are not fully implemented
- **Limited Error Handling**: Some edge cases may not be properly handled
- **No Production Configuration**: Requires additional setup for production deployment
- **Limited Test Coverage**: Test suite needs expansion

## Future Development (TODO)

### Backend Improvements

- [ ] **Add bulk adding functionality** to solve n+1 query issues
- [ ] **Implement foreign key constraints** for data integrity
- [ ] **Add comprehensive data validation** across all entities
- [ ] **Replace persistent entity** with DTO pattern for better separation
- [ ] **Implement search endpoints** for advanced querying capabilities

### Frontend Enhancements

- [ ] **Complete missing UI sections** for all disease types
- [ ] **Add comprehensive medical record operations** (edit, delete, archive)
- [ ] **Implement therapy management features** (add, modify, track responses)
- [ ] **Replace mock data with real statistics** for dashboard analytics
- [ ] **Modernize frontend framework** (consider migration to Angular 2+ or React)

### General Improvements

- [ ] **Add comprehensive test suite** (unit and integration tests)
- [ ] **Implement proper logging strategy** with log levels and rotation
- [ ] **Add API documentation** (Swagger/OpenAPI)
- [ ] **Configure production deployment** with proper profiles
- [ ] **Add monitoring and health checks**
- [ ] **Implement backup and recovery procedures**

## Contributing

Since this is a legacy project, contributions should focus on:

1. **Bug fixes** for existing functionality
2. **Security updates** for dependencies
3. **Documentation improvements**
4. **Test coverage expansion**

For major modernization efforts, consider creating a new version with updated technology stack.

## Support

This is a legacy project developed in 2018. While basic functionality is working, support is limited. Please refer to
the documentation and code comments for understanding the implementation.
