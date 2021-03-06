# exampleREST
Example project for REST based login and registration services

* Exposes RESTful services for user login and registration based on Jersey.
* You need to have Java7, Maven and a Web Server (eg Tomcat) in order to build and run this project.
* If you want to run on Eclipse, you can import this as a Maven Project.
* War file can also be generated by running a clean install maven from command line.
* You can also directly copy the WAR file in target folder to Web Server.
* Services will run on localhost:8080/LoginServices/rest/services (assuming Tomcat running on localhost: 8080)

There are 3 exposed services

* Test : GET service running on localhost:8080/LoginServices/rest/services/echo/{test}
This echoes the {test} param. Can use to test if server is alive.

* Register: POST on localhost:8080/LoginServices/rest/services/register
Used to register new user. Consumes JSON in form of : {"userId":"user1","password":"pass1"}

* Auth: POST running on  localhost:8080/LoginServices/rest/services/auth
Used to authorize user and set cookie in Response. Consumes JSON in form of : {"userId":"admin","password":"admin"}
Sets a cookie named last_access_t in the Response on successful authentication. This will contain last access UTC timestamp.
Cookie can be seen on Network tab of Chrome developer tools or in testing tools like Postman.

* UserId: admin, Password: admin is already present in Server.

* User data lives in memory and is persistent only till server is alive.
