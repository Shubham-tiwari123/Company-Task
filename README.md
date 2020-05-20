## SOLUTION

Developed the APIs using Spring-boot. 

I have used **JWT (JSON Web Token)** for auth mechanism 
of an API and  **MYSQL** as Database to store the data. 


###Packages

**controller:** This is used to handel all api calls.

**dao:** This is used to handel the database related operations.

**entity:** This is used for serialization of the data passed from
client.

**service:** This is used to handel all the functionality 
demanded by the api. Functionalities like database calls 
are handled here.

**util:** This is used to handel all the JWT related works

###Project Flow 

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



