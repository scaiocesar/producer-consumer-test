# Producer-Consumer Proposed Exercise
The current project is a scaffold for an application that receives messages via REST endpoint and
must transfer the message to another endpoint using pure JMS, or a combination of JMS/REST or JMS/SOAP.

There are two projects:
- jms-producer: It is the client application, runs on spring-boot and enables an POST endpoint to post messages
- jms-consumer: Almost empty.

## Details
The method to transfer the message from the Producer should be via JMS. It must be implemented on the com.cinq.rh.jms.Client class.
The way the Consumer receives is up to you: it can be JMS, REST, SOAP. It must be implemented on the com.cinq.rh.jms.Service class. The transfer the message, you must use a third-party software, like an ESB stack, Application Server or a queue manager.

The Producer application is implemented with Spring-boot and is self-contained. To run, just use:

    java -jar producer.war

To assure that the applications are isolated, the Consumer must run on its own VM. Currently the project is self-contained and can be run with :

    java -jar consumer-jar-with-dependencies.war

To test the application using the endpoint, POST a message with a _text/plain_ payload to

    http://localhost:8080/greet/send

I recommend using Advanced Rest Client, a plugin for Google Chrome to POST messages.

#Challenge 1
Transfer the message using a ESB software available on the Market, like Mule, Oracle Service Bus, IBM Integration Bus, Apache Camel, etc. For that, you will need to send evidence and the configuration instructions
so we can replicate the solution.

#Challenge 2
Implement a neat page to send messages
