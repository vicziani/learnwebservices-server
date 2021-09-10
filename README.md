# Repository for learnwebservices.com server application

This repository contains the source of the Spring Boot server application 
that serves the web services on http://learnwebservices.com site.

The main purpose of this site to provide a free, public, sample SOAP webservices
for everyone. These webservices are designed to long live, so they can be
used in tutorials, videos, blog posts, Stack Overflow, etc.

## Run with Maven

If you have at least 11 version of JDK installed, clone the
repository, then run the application with
the following command in the root folder of the project.

```shell
mvnw spring-boot:run
```

Then check the `http://localhost:8080` address!

## Run with Docker

If you have Docker installed run the application with the following command.
No source is needed, it will download the latest Docker image from
Docker Hub.

```shell
docker run -p 8080:8080 --name my-lwsapp vicziani/lwsapp
```

Then check the `http://localhost:8080` address!

## About the author

This site was developed by István Viczián, the author of the http://jtechlog.hu blog.

## Licence

The source code of the SOAP server is licensed under MIT License.

See [LICENCE](LICENCE) files for details.
