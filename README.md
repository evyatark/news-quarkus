Quarkus NewsStand application
=============================

This project is an application that performs web-scraping of news-sites and displays a simpler view of the articles.

This is a Quarkus application using MicroProfile Reactive Messaging, originally taken from AMQP-Quickstart example of Quarkus.

The original quickstart was changed so that all "queues" are internal (in-memory), and no Broker is needed!
 .

## AMQP Broker

I have modified this example so it does NOT nead any AMQP broker!
All "queues" are internal "in-memory" queues (using Vert.x Event Bus)

## Start the application

This application uses Java 11.
If you run with Java 8, you will receive build errors.

To make sure you are using Java 11, do:

```bash

evyatar@evyatar-xps:~/work/quarkus/news-quarkus$ sudo update-alternatives --config java
[sudo] password for evyatar: 
There are 3 choices for the alternative java (providing /usr/bin/java).

  Selection    Path                                            Priority   Status
------------------------------------------------------------
  0            /usr/lib/jvm/java-11-openjdk-amd64/bin/java      1111      auto mode
* 1            /usr/lib/jvm/java-11-openjdk-amd64/bin/java      1111      manual mode
  2            /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java   1081      manual mode
  3            /usr/lib/jvm/java-8-oracle/jre/bin/java          1081      manual mode
```
and make sure you select a Java 11.

In addition, set JAVA_HOME to point to the same JDK:

```bash
evyatar@evyatar-xps:~/work/quarkus/news-quarkus$ env | grep JAVA
JAVA_HOME=/usr/lib/jvm/java-8-oracle
evyatar@evyatar-xps:~/work/quarkus/news-quarkus$ export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
evyatar@evyatar-xps:~/work/quarkus/news-quarkus$ env | grep JAVA
JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
evyatar@evyatar-xps:~/work/quarkus/news-quarkus$
```

The application can be started (from a terminal) using: 

```bash
mvn quarkus:dev
```  

Then, open your browser to http://localhost:8080/prices.html, and you should see a fluctuating price.

As a simpler test, the endpoint (GET) http://localhost:8080/prices returns "hello"

## Anatomy

In addition to the `prices.html` page, the application is composed by 3 components:

* `PriceGenerator` - a bean generating random price. They are sent to an internal channel.
* `PriceConverter` - on the consuming side, the `PriceConverter` receives the message from the channel and convert the price.
The result is sent to an in-memory stream of data
* `PriceResource`  - the `PriceResource` retrieves the in-memory stream of data in which the converted prices are sent and send these prices to the browser using Server-Sent Events.

The interaction with "queues" is managed by MicroProfile Reactive Messaging.
The configuration is located in the application configuration.
(in the configuration, I have removed definition of AMQP Broker!)

In this example - do NOT run any Broker using docker-compose - it is not needed, and will not be used anyway!!