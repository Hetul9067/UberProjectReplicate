


# Uber Application

This Java application is a clone of the popular ride-sharing service Uber. It replicates core functionalities of Uber, allowing users to request rides, drivers to accept requests, and facilitates the entire ride process.

## Features

- **User Authentication**: Users can create accounts and log in securely.
- **Ride Request**: Users can request rides by specifying their pickup and drop-off locations.
- **Driver Assignment**: Drivers receive ride requests and can choose to accept or reject them.
- **Real-Time Tracking**: Users can track the location of their assigned driver in real-time.
- **Wallet Integration**: Wallet feature for payment.

## Technology Used

- **Java (JDK – 20)**: Core programming language used for backend development.
- **Spring Boot**: Framework for building Java-based applications.
- **Hibernate**: ORM tool for database interaction.
- **Lombok**: Library that reduces boilerplate code by automatically generating common methods like getters, setters, and constructors during compile time through annotations.
- **Jackson Data Format XML**: Library for parsing and generating XML data formats, providing seamless integration with XML-based applications.
- **Tomcat**: Servlet container that implements the Java Servlet and Java Server Pages specifications, providing a platform for deploying and running Java web applications.
- **Spring Boot Security**: Module that provides authentication and authorization support for Spring Boot applications, enabling secure access control and user management.
- **JSON Web Token (JWT)**: Compact, URL-safe means of representing claims to be transferred between two parties, often used for stateless authentication and authorization in web applications.
- **Java Persistence API (JPA)**: Specification that provides a framework for managing relational data in Java applications through object-relational mapping (ORM).
- **Google Distance API**: Service that provides travel distance and time estimates based on various transportation modes between multiple origins and destinations.

## Tools

- **Postman**: API client tool used for testing and debugging HTTP APIs by sending requests and inspecting responses.
- **Maven**: Build automation tool that manages dependencies, compiles code, and facilitates project management using XML configuration files.

## Design Patterns

- **Builder Design Pattern**: Separates the construction of complex objects from their representation, allowing the creation of different object configurations through a builder class.
- **Factory Design Pattern**: Provides an interface for creating objects in a superclass while allowing subclasses to alter the type of objects that will be created.
- **Singleton Design Pattern**: Ensures that a class has only one instance and provides a global point of access to that instance.
- **Chain of Responsibility**: Behavioral design pattern where a request is passed through a chain of handlers, each capable of processing or passing it to the next handler in the chain.

## Login Instructions

- **User Name**: hetul@gmail.com
- **Password**: Hetul@123

For login, you have to use this ID and Password.

## Setup Instructions

### Registration: (POST METHOD)

http://localhost:8080/passenger/signup/create

For registration, copy this link and then pass the JSON body. As a result, you will get a response indicating success or error.


{
    "name": "John",
    "gender": "m",
    "phoneNumber": "1214567890",
    "email": "john@gmail.com",
    "password": "John@123",
    "on_ride": "false",
    "isTeenAccount": "false"
}


### Login: (POST METHOD)

http://localhost:8080/auth/passenger/login

Use this link for login and pass the request inside the JSON body. You will receive a JWT token for subsequent steps.


{
    "email": "seemant@gmail.com",
    "password": "Seemant@123"
}


### Display Account Information: (GET METHOD)

http://localhost:8080/passenger/account/

For account visibility, use this link and pass the JWT token for authorization.

### Book Ride: (POST METHOD)

http://localhost:8080/passenger/account/bookride

Use this link to book a ride. Pass the JWT token and the following JSON query inside the body:


{
    "pickUpLocation": "1280 St. Marc Montreal Ca",
    "destinationLocation" : "750 cote de la place D'arms Montreal Ca"
}


### Accept the Ride: (POST METHOD)

http://localhost:8080/passenger/account/bookride/accept

To accept the ride, use this link, pass the JWT token, and the following JSON query:


{
    "acceptTheRide": "true"
}


### Update Account: (PUT METHOD)

http://localhost:8080/passenger/account/update

Use this link to update the account. Pass the JWT token and the following JSON query inside the body:


{
    "passenger_id": 1,
    "name": "Hetull",
    "gender": "m",
    "phoneNumber": 12345678900,
    "email": "hetul@gmail.com",
    "password": "$2a$10$3SQTuczuPLGf.xIJ9n1UZ.123CcPROR1IWzUGX5Wmln8N7KRW4h76",
    "on_ride": false,
    "role": "ROLE_USER",
    "wallet": {
        "wallet_id": 1,
        "money": 200.0
    },
    "bookRide": {
        "bookRideId": 1,
        "pickUpLocation": null,
        "destinationLocation": null,
        "requestDriverU": null,
        "fareIn$": 0.0,
        "distanceInKm": 0.0,
        "duration": null,
        "acceptTheRide": false
    },
    "teenAccount": false
}


### Delete Account

http://localhost:8080/passenger/account/delete

Use this link and pass the JWT token to delete the account.

### Adding Amount in Wallet

http://localhost:8080/passenger/wallet/add

Add the amount in your wallet by using this link, pass the JWT token, and the following JSON query inside the body:


{
    "money" : "50"
}


### Logout
For this step, send the request and pass the JWT token.

## Future Improvement

- **Multi-platform Support**: Develop mobile apps for Android and iOS platforms.
- **Enhanced Security**: Implement additional security measures to protect user data and transactions.
- **Advanced Features**: Introduce features like ride scheduling, multi-stop trips, and fare estimation.


You can copy this content and save it as a `README.md` file in your GitHub repository.
