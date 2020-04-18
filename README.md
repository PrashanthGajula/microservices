# microservices

Reference: https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZSKAFG6aCDVDP86Qx4lNas
- Communication and Service Discovery
- Fault tolerance and resilience

## Running multiple instances of a service in different ports.

Default way to run a service/java main method. It makes use of the default port or the port mentioned in the application.properties file
- java -jar movie-info-service-1.0.0.SNAPSHOT.jar

Run a service using a different port number: 
- java -Dserver.port=8084 -jar movie-info-service-1.0.0.SNAPSHOT.jar

