package com.hamdiBouallegue.hellosigndemo.service;

import org.springframework.stereotype.Service;

import com.hellosign.sdk.HelloSignClient;
import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.SignatureRequest;
import com.hellosign.sdk.resource.TemplateSignatureRequest;

@Service
public class HelloSignService {

	/**
	 * Send a Template document to a client
	 * 
	 * @return json response
	 * @throws HelloSignException
	 */
	public String send() throws HelloSignException {
		HelloSignClient client = new HelloSignClient("f74096a2c85520f15dc7ce477b643239df5dca8ae610c540f5993d943b60b032");
		TemplateSignatureRequest request = new TemplateSignatureRequest();
		request.setSubject("The Email subject");
		request.setSigner("Client", "moustahfidayoub12@gmail.com", "declaration anapec");
		// this is the fullName feild
		request.setCustomFieldValue("name", "ayoub moustahfid");
		request.setTemplateId("a779d0d070507f470dcda4ad6fcd6f6366dc6f45");
		request.setTestMode(true);
		SignatureRequest newRequest = client.sendTemplateSignatureRequest(request);

		return newRequest.toString();
	}
}
