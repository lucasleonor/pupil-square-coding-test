This application uses an in memory database, so everytime you stop the application you're going to lose all the data
that was sent to it.

### Building

To build this application simply run

```shell
./mvnw clean package
```

and a jar file will be created under `target/square-coding-test-0.0.1-SNAPSHOT.jar`

### Running

To run it you only need `Java` installed, then simply run

````shell
java -jar target/square-coding-test-0.0.1-SNAPSHOT.jar
````

Alternatively you can run it using `Docker`, to do this first you need to build the Docker Image running

````shell
docker build -t square-coding-test .
````

and then run it

````shell
docker run -p 8080:8080 square-coding-test
````

### Consuming

To consume this application you can:

* Make a `GET` request to `/shapes` to list all shapes stored, this endpoint is paginated.
    ````shell
    curl --request GET http://localhost:8080/shapes
    ````
* Make a `POST` request to `/shapes` to store a new shape, this will check if the shape is already stored (same name)
  and if it collides with another shape
    ````shell
    curl -X POST "http://localhost:8080/shapes" -H "Content-Type: application/json" -d "{\"type\": \"SQUARE\",\"name\": \"test\",\"geometryDescriptor\":{\"x\": 1,\"y\": 1,\"size\": 1}}"
    ````

