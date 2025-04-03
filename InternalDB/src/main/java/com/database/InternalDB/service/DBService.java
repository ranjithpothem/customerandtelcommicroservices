package com.database.InternalDB.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.database.InternalDB.entity.SendMsg;
import com.database.InternalDB.entity.Users;
import com.database.InternalDB.repo.SendMsgRepository;
import com.database.InternalDB.repo.UsersRespository;

@Service
public class DBService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UsersRespository usersRespository;

	@Autowired
	private SendMsgRepository sendMsgRepository;

	public ResponseEntity<Long> validateTheDetails(String username, String password) {

		Optional<Users> user = usersRespository.findByUsernameAndPassword(username, password);
		Long accountId = user.map(Users::getAccountID).orElse(null);
		return ResponseEntity.ok(accountId);
	}

	public ResponseEntity<List<Users>> findByAccountId(Iterable<Long> accountId) {

		List<Users> allById = usersRespository.findAllById(accountId);
		return ResponseEntity.ok(allById);
	}

	public ResponseEntity<Object> insertTheMessage(Long mobile, String message, Long account_id) {

		SendMsg msg = new SendMsg(mobile, message, account_id);
		msg.setStatus("new");
		msg.setReceivedTs(new Date());
		msg = sendMsgRepository.save(msg);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(msg);
	}

}
