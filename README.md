# Producer-Consumer Proposed Exercise
This is my implementation of cinq excercise.
-In this excercise I use AWS SQS to make the queues communication.

The Producer application can be verified by running: 

    java -jar producer.war

To assure that the applications are isolated, the Consumer must run on its own VM. The Consumer must run with the following command :

    java -jar consumer-jar-with-dependencies.war

To test the application using the endpoint, we will POST a message with a _text/plain_ payload to: 

    http://localhost:8080/greet/send

