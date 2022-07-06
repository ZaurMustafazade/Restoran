# Restoran

About this application.

```
This application is written in any for a restaurant. In the admin role, all items create, update or delete with success. In the waiter role, only can choose the product. All the codes in this program were written only by me.
```

##How to use this code?

1. Make sure you have Java 8 and Gradle installed
2. Fork this repository and clone it

```
http://localhost:8082/swagger-ui.html#/
```
3.Navigate into the folder
```
 cd spring-boot-jwt
```
4.Install dependencies
```
 gradle install
```
5.Run the project
```
spring-boot:run
```
6.Navigate to http://localhost:8082/swagger-ui.html in your browser to check everything is working correctly. You can change the default port in the application.yml file
```
server:
  port: 8082
```
7.Make a GET request to /users/me to check you're not authenticated. You should receive a response with a 403 with an Access Denied message since you haven't set your valid JWT token yet
```
 GET http://localhost:8082/users/me
```
8.Make a POST request to /users/signin with the default admin role we programatically created to get a valid JWT token
```
 POST 'http://localhost:8082/users/signin?username=admin&password=admin'
```
9.Add the JWT token as a Header parameter and make the initial GET request to /users/me again
```
 GET http://localhost:8082/users/me -H 'Authorization: Bearer <JWT_TOKEN>'
```
10.And that's it, congrats! You should get a similar response to this one, meaning that you're now authenticated
```
{
  "id": 1,
  "username": "admin",
  "email": "admin@email.com",
  "roles": [
    "ROLE_ADMIN"
  ]
}
```
---

##How to use this application in a waiter role?

1.Make a POST request to /users/signin with the default waiter role we programatically created to get a valid JWT token
```
 POST 'http://localhost:8080/users/signin?username=waiter&password=waiter_1'
```
2.Add the JWT token as a Header parameter and make the initial GET request to /users/me again
```
 GET http://localhost:8080/users/me -H 'Authorization: Bearer <JWT_TOKEN>'
```
3.And that's it, congrats! You should get a similar response to this one, meaning that you're now authenticated
```
{
  "id": 1,
  "username": "waiter",
  "email": "waiter@email.com",
  "roles": [
    "ROLE_WAITER"
  ]
}
```
---

##How to can we include the product?
1. First of all, you entered an admin role.
2. Application return JWT Token. Add the JWT token as a Header parameter
3. Make a POST request to /menus and sent product in JSON format
```
curl -X POST "http://localhost:8082/menu" -H "accept: */*" -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifV0sImlhdCI6MTY1NTQ3NDQ1OCwiZXhwIjoxNjU1NDc4MDU4fQ.1C9wY89WBILrldpmUBj8k9xBu1PmMyJ8dqbwneFJRgc" -H "Content-Type: application/json" -d "{ \"foodId\": 0, \"foodName\": \"Hamburger\", \"foodType\": \"BURGERS\", \"id\": 0, \"info\": \"Burger made of a 100% pure beef patty, onions, pickles and just the right amount of mustard and ketchup wrapped in a caramelized bun.\",  \"price\": \"5\"}"
```
4Application return HTTP status 200 and response body.
```
{
  "id": 3,
  "foodType": "BURGERS",
  "foodName": "Hamburger",
  "info": "Burger made of a 100% pure beef patty, onions, pickles and just the right amount of mustard and ketchup wrapped in a caramelized bun.",
  "price": "5"
}
```
