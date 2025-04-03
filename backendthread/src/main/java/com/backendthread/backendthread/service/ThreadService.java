package com.backendthread.backendthread.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.backendthread.backendthread.Entity.SendMsg;
import com.backendthread.backendthread.repo.SendMsgRespository;

@Component
public class ThreadService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SendMsgRespository sendMsgRespository;

	@Value("${telecomoperator.api.url}")
	private String telecomUrl;

	@Scheduled(fixedRate = 1000)
	public void processMessage() {


		List<SendMsg> sendMsgList = sendMsgRespository.findNewMessages();

		for (SendMsg msg : sendMsgList) {
			Long accountId = msg.getAccountId();
			long mobile = msg.getMobile();
			String message = msg.getMessage();
			long id = msg.getId();

			sendMsgRespository.updateStatusToInprocess(id);

			// calling telcomoperator
			String response = restTemplate.postForObject(
					telecomUrl + "/smsc?accountId={accountId}&mobile={mobile}&message={message}", null, String.class,
					accountId, mobile, message);

			sendMsgRespository.updateSendMsg(response, id);

		}
		
		List<SendMsg> sendMsgList1 = sendMsgRespository.findInprocessMessages();

		for (SendMsg msg : sendMsgList1) {
			Long accountId = msg.getAccountId();
			long mobile = msg.getMobile();
			String message = msg.getMessage();
			long id = msg.getId();

			// calling telcomoperator
			String response = restTemplate.postForObject(
					telecomUrl+"/smsc?accountId={accountId}&mobile={mobile}&message={message}",
					null, String.class, accountId, mobile, message);

			sendMsgRespository.updateSendMsg(response, id);

		}

	}

}
