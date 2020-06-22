Quarkus AMQP 1.0 Quickstart
============================

This project illustrates how you can interact with AMQP 1.0 (in this quickstart no Broker is needed!) using MicroProfile Reactive Messaging.

## AMQP Broker

I have modified this example so it does NOT nead any AMQP broker!
All "queues" are internal "in-memory" queues (using Vert.x Event Bus)

## Start the application

The application can be started using: 

```bash
mvn quarkus:dev
```  

Then, open your browser to `http://localhost:8080/prices.html`, and you should see a fluctuating price.

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