This MVP backend application manages stock & stock exchanges.

How to run?

If you have maven installed, please run the below command at the root of the project

`mvn spring-boot:run`

The application endpoints are secured, in order to use them, the below credentials provided:

> Username: user
> Password: pass

If you want to change these credentials to something else, **application.properties** file can be updated

Swagger endpoint can be accessed with the below link

> http://localhost:8080/swagger-ui/index.html#/

There are 13 unit tests written, to run them, use the following command:

`mvn test`
