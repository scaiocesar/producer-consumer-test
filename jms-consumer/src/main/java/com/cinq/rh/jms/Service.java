package com.cinq.rh.jms;

import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;



public class Service implements Runnable {
	private static String SQS_URL = "https://sqs.us-east-1.amazonaws.com/554294572701/cinq-producer-consumer-test";
	
	public static void main(String args[]) {
		System.setProperty("aws.accessKeyId", "AKIAJY4EVMRTEV7JSDHA");
		System.setProperty("aws.secretKey", "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");
		System.out.println("Send the messages....");
		Thread thread = new Thread(new Service());
		thread.start();
	}

	public void run() {
		// Loop receiving messages
		
		AWSCredentials credentials = null;
		try {
			credentials = new SystemPropertiesCredentialsProvider().getCredentials();
		} catch (Exception e) {
			System.out.println("Credential Error: "+e.getMessage());
		}

		AmazonSQS sqs = new AmazonSQSClient(credentials);
		Region usWest2 = Region.getRegion(Regions.US_EAST_1);
	    sqs.setRegion(usWest2);

		try {
			// Receive messages
			ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(SQS_URL);
			while(true){
				List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
				for (Message message : messages) {
					System.out.println("----------------" );
					System.out.println("Your message is:  " + message.getBody());
				}
				if(messages != null && messages.size() > 0){
					String messageReceiptHandle = messages.get(0).getReceiptHandle();
					sqs.deleteMessage(new DeleteMessageRequest(SQS_URL, messageReceiptHandle));
					System.out.println("now we'll delete your message =) " );
					System.out.println("----------------" );
				}
			}

		} catch (AmazonServiceException ase) {
			System.out.println("AmazonServiceException Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("AmazonClientException Error Message: " + ace.getMessage());
		}
	
		
	}
	
		
}
