# 👟 DSnacker Store *| shoes online shop* (*released v1.0*)
### Author: **[Duc Dao](https://beacons.ai/ducdmd152)**
### Released version: [ DSnacker Store 1.0](https://github.com/ducdmd152/dsnackerstore/releases/tag/1.0)
### #springwebapplicationdevelopment
--------------------------------------------------
## Table of Contents
- [Description](#description)
- [Live Demo](#live-demo--here)
- [Technology](#technology)
- [How can get started](#how-can-get-started)
- [License & Copyright](#license--copyright)
--------------------------------------------------
## Description

 - A **shoe online shop** web application.
 - Users are divided into **4 main roles**: Guest/CUSTOMER, EMPLOYEE, OWNER, ADMIN
	 - All roles are authenticated through the Login feature;	
	 - In addition, guests are also supported the Register feature with CUSTOMER role.
	 - Note*: *You can operate as a customer and buy the book without login like a guest.*
 - **As a CUSTOMER**, we can do some operations to order shoes like:
    - View **products** in the store.
    - **Cart’s actions**:
        - Add products to the cart.
        - View products in the cart.
        - Remove products from the cart.
    - **Checkout**:
        - View details of the prepared order. 
        - Fill in the delivery information.  
        - Receive the corresponding bill/information about the order.  
    - **View his/her order**:
        - View list of orders. 
        - View details of each order.
 - **As an EMPLOYEE**, we can do some operations :
	-  Manage **products** in store like view product list, create new product, update exist product,...
	-  Manage **orders** from customers like view orders with status *(all, pending, confirmed, refused)* ; and also confirm/refuse orders in pending order list.
 - **Expected features in version 2.0**:
 	- **As an CUSTOMER**, we can cancel orders what are still in pending status (NOT YET confirmed/refused by employees).
	- **As an OWNER**, we can do some operations :
		 - Manage employees with CRUD actions.
		 - View the statistics about activities in the store.
		 - ...
	 - **As an ADMIN**, we can do some operations :
		 - Manage accounts in the system.
		 - View the information about the system *(loged info, health check, traffic info,...)*
		 

- ***Notice**:*
	- *This web is tested for screens and features on desktops; not yet for mobile.*

## Live Demo: 👉 [here](https://youtu.be/hNdUvkhrdkY)

## Technology

**1. Frontend**

- HTML, CSS
- Thymeleaf

**2. Backend**

- Spring MVC.
- Spring Security.
- Spring Data JPA.

**3. Platforms & tools**

- Spring Boot.
- Maven.
- IDE: Eclipse
- DBMS: MySQL.

## How can Get Started

- **Source/Project**:
    - Download the source code.
- **Database**:
    - Execute the [Dsnackerstore_SCRIPT.sql](https://github.com/ducdmd152/dsnackerstore/blob/main/sql-scripts/Dsnackerstore_SCRIPT.sql) in sql-scripts folder.
    - Customize the information in [application.properties](https://github.com/ducdmd152/dsnackerstore/blob/main/src/main/resources/application.properties) in /src/main/resources/application.properties for adapting to your case.
   
- **Already accounts** to login in the system:

	| Username | Password | Role     |
	|----------|----------|----------|
	| tueminh  | 123456   | CUSTOMER |
	| baoanh   | 123456   | EMPLOYEE |

## License & Copyright
&copy; 2023 Duc Dao ducdmd152 Licensed under the [GNU LICENSE](https://github.com/ducdmd152/dsnackerstore/blob/main/LICENSE).

> 🤟 Feel free to use my repository and star it if you find something interesting 🤟
