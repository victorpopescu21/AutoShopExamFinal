# AutoShopExamFinal

This project is an online shop which has as products cars, motorbikes and bicycles. They are all inheriting the product class which defines all the attributes used in the project.
This is a demonstration of Spring Boot 3.0 implementation.

## Features
* User registration and login with basic Spring Security authentication
* Password encryption with BCrypt
* Role-based authorization with Spring Security
* Add products to shopping cart and convert shopping cart to order
* Search of products by category/price
* Logout mechanism
* Accessing controller through front-end implemented with Thymleaf

## Technologies
* Spring Boot 3.0
* Hibernate
* Maven
* Spring Security
* BCrypt
* Maven
* Thymleaf


## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+

To build and run the project, follow these steps:

* Clone the repository: `git clone https://github.com/victorpopescu21/AutoShopExamFinal.git`
* Navigate to the project directory: cd AutoShopExamFinal
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run

### Alternative
* Simply install IntelliJ IDEA CE available for download at `https://www.jetbrains.com/idea/download` (Community Edition)
* Open File > New > Project from Version Control
* Add in the URL section: `https://github.com/victorpopescu21/AutoShopExamFinal.git`
* Make sure Git is installed on Intellij
* Last, but not the least, Run the `AutoShopApplication` available in `ro.itschool` package

-> The application will be available at http://localhost:8080.