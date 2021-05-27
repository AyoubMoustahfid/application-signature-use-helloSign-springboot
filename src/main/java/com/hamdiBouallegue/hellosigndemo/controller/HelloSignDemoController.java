package com.hamdiBouallegue.hellosigndemo.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hamdiBouallegue.hellosigndemo.service.HelloSignService;
import com.hellosign.sdk.HelloSignException;
import com.hellosign.sdk.resource.Event;

@RestController
@RequestMapping("/api")
public class HelloSignDemoController {
	private Logger logger = LoggerFactory.getLogger(HelloSignDemoController.class);

	@Autowired
	HelloSignService helloSignService;

	@PostMapping("/sign")
	public String send() throws HelloSignException {
		return helloSignService.send();
	}

	@PostMapping(value = "/hellosign/webhook")
	public String webhook(@RequestParam String json) throws HelloSignException, JSONException {
		JSONObject jsonObject = new JSONObject(json);
		Event event = new Event(jsonObject);

		boolean validRequest = event.isValid("05445b55343176fbbbe22c20f12d7898c0d68765dcb5a12a04588585199082f3");

		if (validRequest) {

			switch (event.getTypeString()) {
			case "signature_request_signed":
				logger.info("Signature Request Signed");
				break;
			case "signature_request_sent":
				logger.info("Signature Request Sent");
				break;
			default:
				break;
			}
		}
		return "Hello API Event Received";
	}
}
