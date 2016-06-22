package com.cinq.rh.jms;

import org.springframework.stereotype.Component;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@Component
public class Client {

	private static String SQS_URL = "https://sqs.us-east-1.amazonaws.com/554294572701/cinq-producer-consumer-test";

	
	public boolean sendAMessage(String greeting) {
		
		return sendSQS(greeting);
	}

	private boolean sendSQS(String myMessage) {
		System.setProperty("aws.accessKeyId", "AKIAJY4EVMRTEV7JSDHA");
		System.setProperty("aws.secretKey", "uXnB15Dvm/YvvNjVYb1A0fOriUu6BdbYLhOe1xWK");
		/*
		 * The ProfileCredentialsProvider will return your [default] credential
		 * profile by reading from the credentials file located at
		 * (~/.aws/credentials).
		 */
		AWSCredentials credentials = null;
		credentials = new SystemPropertiesCredentialsProvider().getCredentials();

		AmazonSQS sqs = new AmazonSQSClient(credentials);
		Region usWest2 = Region.getRegion(Regions.US_EAST_1);
	    sqs.setRegion(usWest2);

		try {
			// Send a message
			System.out.println("Sending a message to MyQueue.\n");
			sqs.sendMessage(new SendMessageRequest(SQS_URL, myMessage));
			return true;
		} catch (AmazonServiceException ase) {
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Error Message: " + ace.getMessage());
		}
		return false;
	}
	
	

}
