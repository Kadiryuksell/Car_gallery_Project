# 🚗 Car Gallery Simulation

This project is a simulation of a car gallery system. Users can view the vehicles available in the gallery and purchase cars that are in a **salable** condition. If a car has already been **saled**, users cannot access or interact with it. The project includes basic business rules that model a real-world car gallery system.

## 📌 Features

- Vehicle listing
- Status distinction between **salable** and **saled**
- Simple purchase operation
- Basic user-vehicle interaction
- API documentation with Swagger/OpenAPI

## 👨‍💻 Usage Scenario

1. The user can list all vehicles in the system.
2. Each vehicle's sale status (`salable`, `saled`) is clearly displayed.
3. The user can only purchase vehicles that are marked as `salable`.
4. After a purchase, the vehicle's status is updated to `saled`.

## 🚀 Installation

### Requirements
- Java 17+
- Maven 3.8+
- PostgreSQL database
- IDE: Spring Tool Suite, IntelliJ IDEA or VSCode

### Steps

1. Clone the repository:
```bash
git clone https://github.com/Kadiryuksell/Car_gallery_Project.git 
cd [path-to-project-directory]
```
2. Create a schema named `car_gallery` in your PostgreSQL database.  
Then, configure the following properties in the `application.properties` file:

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=create
```
> ⚠️ Important: After the project is built and running for the first time,  
make sure to change `spring.jpa.hibernate.ddl-auto=create` to `update` in the `application.properties` file.  
Otherwise, the database schema will be reset every time the application starts.

## 🚀 Build and Run the Project

1. Open a terminal or command prompt in the project directory.

2. To build and run the project, execute the following commands:

```bash
mvn clean install
mvn spring-boot:run
```
If the application starts successfully, you can access the API documentation at:  
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🗄️ Entity-Relationship Diagram
![Screenshot_1](https://github.com/user-attachments/assets/8c0d2567-96e2-468f-bbac-47f7859665e1)

## 📑 Swagger API Documentation
**Note:** The token used for authentication is valid for 4 hours. <br>
All REST API endpoints used in the project are automatically documented with Swagger (OpenAPI).  
Below is an example of the Swagger interface:

![1](https://github.com/user-attachments/assets/f701bccb-145b-4c6c-8059-ca56a7fc4554)
![2](https://github.com/user-attachments/assets/a414bf9e-5b99-4876-8a4b-6183206e35dc)



