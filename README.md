# Producer-Consumer Proposed Exercise
The current project is a scaffold for an application you must write to receive messages via REST endpoint and
transfer them to another endpoint using Queues (JMS like), using a ESB software (or similar) available on the Market, like Mule, Oracle Service Bus, IBM Integration Bus, Apache Kafka, etc. For that, you will need to send evidence and the configuration instructions so we can replicate the solution. 


![Ideal Application](https://github.com/cinqtechnologies/producer-consumer-test/blob/master/IdealApplication.jpg?raw=true)


Included you will find two projects:
- jms-producer: It is the client application, runs on spring-boot and enables an POST endpoint to post messages
- jms-consumer: Almost empty.

## Details to implement the application
- The method to transfer the message from the Producer should be via JMS.
- It must be implemented on the com.cinq.rh.jms.Client class.
- The way the Consumer receives is up to you: it can be JMS, or a proprietary method like Hazelcast, RabbitMQ, Apache Kafka, NSQ, etc.
- The consumer must be implemented on the com.cinq.rh.jms.Service class. The transfer the message, you must use a third-party software, like an ESB stack, Application Server or a queue manager. The intention is to simulate an environment were the middleware can be scalable without interfering in the application. 

## Verification of the results
We won't accept any pre-built artifact. All the verifications will be performed on the command line.

We will build the application in one-pass, with:

    mvn clean package

The Producer application will be verified by running: 

    java -jar producer.war

To assure that the applications are isolated, the Consumer must run on its own VM. The Consumer must run with the following command :

    java -jar consumer-jar-with-dependencies.war

To test the application using the endpoint, we will POST a message with a _text/plain_ payload to: 

    http://localhost:8080/greet/send

We expect that the message will be delivered to the log file or console on the Consumer side, at least.

For your tests, we recommend using a plugin for Google Chrome to POST messages, like Postman or Advanced Rest Client.

## Other artifacts
- JUnit tests will give you a bonus
- Include __detailed__ instructions on how to install and run the broker of your choice.


#Challenge 2
Implement a neat page to send messages
