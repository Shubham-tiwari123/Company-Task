## SOLUTION

Developed the APIs using Spring-boot. 

I have used **JWT (JSON Web Token)** for auth mechanism 
of an API and  **MYSQL** as Database to store the data. 


### Packages

**controller:** This is used to handel all api calls.

**dao:** This is used to handel the database related operations.

**entity:** This is used for serialization of the data passed from
client.

**service:** This is used to handel all the functionality 
demanded by the api. Functionalities like database calls 
are handled here.

**util:** This is used to handel all the JWT related works

### Project Flow 

1. When user hits the **login API** then user credentials are
verified from the database. If the credentials are 
correct then a **JWT TOKEN** is created and it is send
back to the client is response.

2. When the user hits the **create_event** API then, the
server first extracts the **JWT TOKEN** from the header. 
This token is first verified. If the token is currect then
new event is created else user is not allowed to create the
event.

3. The same process takes place for the other api calls.

### Run a project

#### Steps to  run project in ubuntu

We require **JDK**, **Xampp** for database, **maven** and **curl**
for running this application

1. To install JDK
    
        sudo apt install default-jdk
        
2. To check if JDK is installed or not:
        
        java -version
        
3. Clone the project from github.

4. Open the project folder

5. Open terminal in the folder to install maven. To install mvn use the following
command:

        sudo apt install maven
        
6. Now to install pom dependencies use the command:
        
         mvn clean install
         
7. Now to run the project use the command:

        mvn spring-boot:run
        
8. The application will start on port:8081

9. To hit an api we require **curl**. Open a new terminal and install curl using
    
        sudo apt install curl
        

### Curl commands for APIs

**For Registration API**
        
         curl -X POST \
         http://localhost:8081/register_user \
         -H 'Cache-Control: no-cache' \
         -H 'Content-Type: application/json' \
         -d '{
         	"userEmail":"ab@gmail.com",
         	"password":"abc",
         	"name":"sh4"
         }'
         
**For Login API**
        
        curl -X POST \
        http://localhost:8081/login_user \
        -H 'Content-Type: application/json' \
        -H 'Cache-Control: no-cache' \
        -d '{
        	"userEmail":"ab@gmail.com",
        	"password":"abc"
        }'
        
        
**For create_event API**
        
        curl -X POST \
          http://localhost:8081/create_event \
          -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiQGdtYWlsLmNvbSIsImV4cCI6MTU5MDA3MTEzMSwiaWF0IjoxNTkwMDM1MTMxfQ.E0tQ9ZzNT-jO7WSGw491muvO7wcTT_jrbQLDqSIXGsc' \
          -H 'Cache-Control: no-cache' \
          -H 'Content-Type: application/json' \
          -d '{
        	"eventName":"new party",
        	"eventDate":"20 may",
        	"place": "mumbai"
        }'
 
 
 **For get event list**
 
       curl -X GET \
         http://localhost:8081/event_list \
         -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiQGdtYWlsLmNvbSIsImV4cCI6MTU5MDA3MTEzMSwiaWF0IjoxNTkwMDM1MTMxfQ.E0tQ9ZzNT-jO7WSGw491muvO7wcTT_jrbQLDqSIXGsc' \
         -H 'Cache-Control: no-cache' \
         -H 'Content-Type: application/json' \
         
 
 **For delete event** (This api require event id)
        
        curl -X DELETE \
          http://localhost:8081/delete_event/7 \
         -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiQGdtYWlsLmNvbSIsImV4cCI6MTU5MDA3MTEzMSwiaWF0IjoxNTkwMDM1MTMxfQ.E0tQ9ZzNT-jO7WSGw491muvO7wcTT_jrbQLDqSIXGsc' \
         -H 'Cache-Control: no-cache' \
         -H 'Content-Type: application/json' \
        
**For update event** (This api also require event id)

        curl -X PUT \
          http://localhost:8081/update_event/8 \
          -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiQGdtYWlsLmNvbSIsImV4cCI6MTU5MDA3MTEzMSwiaWF0IjoxNTkwMDM1MTMxfQ.E0tQ9ZzNT-jO7WSGw491muvO7wcTT_jrbQLDqSIXGsc' \
          -H 'Cache-Control: no-cache' \
          -H 'Content-Type: application/json' \
          -d '{
            "eventName":"new party",
            "eventDate":"30-may",
            "place":"delhi"
        }'
        
**Note:** 
1. If server shows expiration of token key then run hit login api and 
it will return the jwt token again. 

2. Change value for **Authorization: Bearer** is every request with the value
returned by login api

### Database Schema

Creating database **company**

    CREATE DATABASE company

Creating table **register_user**

    CREATE TABLE register_user(
        userEmail varchar(100) PRIMARY KEY,
        userName varchar(100),
        password varchar(100)
    )
    
Creating table **event_list**

    CREATE TABLE event_list(
        eventID int PRIMARY KEY AUTO_INCREMENT,
        eventName varchar(100),
        eventDate varchar(100),
        eventPlace varchar(100),
        userName varchar(100),
        FOREIGN KEY (userName) REFERENCES register_user(userEmail)
    )

### Response for APIs

**Registration API**

    On Success
    {
        "status": true
    }
    
    On Failure
    {
        "status": false
    }
    
**Login API**

    On Success
    {
        "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYkBnbWFpbC5jb20iLCJleHAiOjE1OTAwNzM4OTMsImlhdCI6MTU5MDAzNzg5M30.ZmZifDs63r8NGBKkqS6Xq30D1o_LJgGbJpDYDgD6CD8",
        "status": "true"
    }
    
    On Failure
    {
        "status": false
    }
    
**Create event API**

    if token verified
        On Success
        {
            "eventStatus": "true"
        }
        
        On Failure
        {
            "eventStatus": "false"
        }
        
    if token not verified
    {
        "timestamp": "2020-05-21T04:51:54.319+00:00",
        "status": 403,
        "error": "Forbidden",
        "message": "",
        "path": "/create_event"
    }
    
**Delete event API**

    if token verified
        On Success
        {
            "deleteStatus": "true"
        }
        
        On Failure
        {
            "deleteStatus": "false"
        }
        
    if token not verified
    {
        "timestamp": "2020-05-21T04:51:54.319+00:00",
        "status": 403,
        "error": "Forbidden",
        "message": "",
        "path":  "/delete_event/7"
    }
    
**Update event API**

    if token verified
        On Success
        {
            "updateStatus": "true"
        }
        
        On Failure
        {
            "updateStatus": "false"
        }
        
    if token not verified
    {
        "timestamp": "2020-05-21T04:51:54.319+00:00",
        "status": 403,
        "error": "Forbidden",
        "message": "",
        "path":  "/update_event/7"
    }
    
**Get All Event API**

    if token verified
        [
            {
                "eventID": 8,
                "eventName": "new party",
                "eventDate": "30-may",
                "place": "delhi"
            },
            {
                "eventID": 9,
                "eventName": "new party2",
                "eventDate": "9-may",
                "place": "mumbai"
            }
        ]
        
    if token not verified
    {
        "timestamp": "2020-05-21T04:51:54.319+00:00",
        "status": 403,
        "error": "Forbidden",
        "message": "",
        "path":  "/event_list"
    }
    
