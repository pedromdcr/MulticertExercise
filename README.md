# MulticertExercise
Interview exercise for a company.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

#### Java 1.7 and JDK 7
This project requires JDK 7 which you can download [here][1].

#### Tomcat 8
[Download][3] Tomcat. Go into your Tomcat 8.x install directory and add the following code to the file `server.xml` adjusting the path to the keystore (the SSL certificate) accordingly:

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

Finally add the following to `tomcat-users.xml`, in case you haven't set it already:

```xml
<role rolename="manager-gui"/>  
<role rolename="manager-script"/>   
<user username="admin" password="password" roles="manager-gui,manager-script" />  
```

Use any `username` and `password` you'd like, but use these when you change Maven settings below.

#### PostgreSQL 9.6.3
This project uses PostgreSQL which you can download [here][2].

There is a database dump file called `multicertdb.sql` in the project root folder. To reload it in your machine open a command prompt in this folder and type the following:

```
psql -d newdb -f multicertdb.sql
```

This reloads multicertdb into a freshly created database named `newdb`.

#### Maven 3.3.9

We use [Maven][4] for dependency management in this project. Make sure to add the following definition to the `settings.xml` file in your Maven install directory (nest it inside the `<settings>` tag):

```xml
<server>
   <id>TomcatServer</id>
   <username>admin</username>
   <password>password</password>
</server>
```

### Installing

Instructions sent via email. Further improvements will be made soon if possible.

## Deployment

Currently only supported via Eclipse with Tomcat.

## Built With

* [Eclipse](https://eclipse.org/) - My Java IDE of choice
* [PostgreSQL](https://www.postgresql.org/) - The RDBMS used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Tomcat](https://tomcat.apache.org/) - Used to deploy web services

[1]: http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase7-521261.html
[2]: https://www.postgresql.org/download/windows/
[3]: https://tomcat.apache.org/download-80.cgi#8.0.44
[4]: http://mirrors.fe.up.pt/pub/apache//maven/maven-3/3.3.9/
