package com.backendthread.backendthread.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backendthread.backendthread.Entity.SendMsg;

import jakarta.transaction.Transactional;

@Repository
public interface SendMsgRespository extends JpaRepository<SendMsg, Long> {

	@Query(value = "SELECT * FROM send_msg1 WHERE status = 'NEW'", nativeQuery = true)
	List<SendMsg> findNewMessages();
	
	@Query(value = "SELECT * FROM send_msg1 WHERE status = 'Inprocess'", nativeQuery = true)
	List<SendMsg> findInprocessMessages();

//	"update send_msg set status='Inprocess' where id=?"
	@Modifying
	@Transactional
	@Query(value = "UPDATE send_msg1 SET status = 'Inprocess' WHERE id = :id", nativeQuery = true)
	void updateStatusToInprocess(@Param("id") Long id);
	
	
	
//	"update send_msg set status='sent',sent_ts=now() ,telco_response=? where id=?",
	@Modifying
    @Transactional
    @Query(value = "UPDATE send_msg1 SET status = 'sent', sent_ts = NOW(), telco_response = :response WHERE id = :id", nativeQuery = true)
    void updateSendMsg(@Param("response") String response, @Param("id") Long id);
}
