 
# 🛒 E-Commerce Product Management System

This is a **Java Spring Boot-based eCommerce backend** application that allows you to manage different types of products such as electronics, home appliances, accessories, etc.

## 🚀 Features

- Add, update, delete, and fetch product details
- Search products by name, description, brand, or category
- Store product details like name, price, brand, quantity, release date, etc.
- Handle large data fields using `@Lob` (for descriptions or images)
- Enable CORS support for frontend integration
- Custom search using `@Query`

## 🧱 Tech Stack

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- H2
- **Hibernate**
- **Lombok**

## 📦 Project Structure
ecommerce/
├── src/main/java/com/ecommerce/
│ ├── controller/ # REST controllers
│ ├── model/ # Product entity
│ ├── repository/ # JPA repository interfaces
│ ├── service/ # Service classes
│ └── EcommerceApplication.java
├── src/main/resources/
│ ├── application.properties
│ └── data.sql / schema.sql
└── pom.xml

## 📝 Sample Product Fields

- `id` (int)
- `name` (String)
- `description` (String, `@Lob`)
- `brand` (String)
- `price` (BigDecimal)
- `category` (String)
- `releaseDate` (Date)
- `available` (boolean)
- `quantity` (int)

## 🔍 Sample Custom Search

```java
@Query("SELECT p FROM Product p WHERE " +
       "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
       "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
       "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
       "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
List<Product> searchProducts(@Param("keyword") String keyword);

Configuration(application.properties)

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true

| Method | Endpoint                       | Description             |
| ------ | ------------------------------ | ----------------------- |
| GET    | `/products`                    | Get all products        |
| POST   | `/products`                    | Add a new product       |
| PUT    | `/products/{id}`               | Update existing product |
| DELETE | `/products/{id}`               | Delete a product        |
| GET    | `/products/search?keyword=...` | Search by keyword       |


**Developed by Upasana Pradhan
Location: Bangalore, India
Experience: 2+ years as Java Developer**

