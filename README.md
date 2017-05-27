# MulticertExercise
Interview exercise for a company.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

#### Java 1.7 and JDK 7
This project requires JDK 7 which you can download [here][1].

#### Tomcat 8
Go into your Tomcat 8.x install directory and add the following code to the file `server.xml` adjusting the path to the keystore (the SSL certificate) accordingly:

```xml
<Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS" 
               keystoreFile="/Users/Pedro/workspace/MulticertWS/multicertKey"
	       keystorePass="multicert" />
```

In the same directory, add the following code to `context.xml` nested inside the `<Context>` tag:

```xml
<Resource name="jdbc/postgres" auth="Container"
          type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
          url="jdbc:postgresql://127.0.0.1:5432/multicertdb"
          username="postgres" password="multicert" maxTotal="20" maxIdle="10" maxWaitMillis="-1"/>
```

#### PostgreSQL 9.6.3


### Installing

A step by step series of examples that tell you have to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Eclipse](https://eclipse.org/) - My Java IDE of choice
* [PostgreSQL](https://www.postgresql.org/) - The RDBMS used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Tomcat](https://tomcat.apache.org/) - Used to deploy web services

[1]: http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html
