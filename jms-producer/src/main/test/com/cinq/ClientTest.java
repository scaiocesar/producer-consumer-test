package com.cinq;

import java.util.List;

import org.junit.Test;

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
import com.cinq.rh.jms.Client;

import junit.framework.TestCase;

public class ClientTest extends TestCase {

	private static String MSG_TEST = "This is a test message to AWS SQS";
	private static String SQS_URL = "https://sqs.us-east-1.amazonaws.com/554294572701/cinq-producer-consumer-test";
	
	@Test
	public void testSendMsg(){
		Client cli = new Client();
		assertEquals(true, cli.sendAMessage(MSG_TEST));
		String rtn = recMsg();
		assertEquals(true, MSG_TEST.equals(rtn));
	}
	
	
	private String recMsg(){
		String myMsg  = "";
		System.setProperty("aws.accessKeyId", "AKIAJY4EVMRTEV7JSDHA");
		System.setProperty("aws.secretKey", "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");
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
			List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
			for (Message message : messages) {
				myMsg =  message.getBody();
			}
			String messageReceiptHandle = messages.get(0).getReceiptHandle();
			sqs.deleteMessage(new DeleteMessageRequest(SQS_URL, messageReceiptHandle));
			System.out.println("now we'll delete your message =) " );
			System.out.println("----------------" );
			return myMsg;
		} catch (AmazonServiceException ase) {
			System.out.println("AmazonServiceException Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("AmazonClientException Error Message: " + ace.getMessage());
		}
		return null;
	}
	
}
