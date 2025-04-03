package com.database.InternalDB.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.database.InternalDB.entity.SendMsg;

import jakarta.transaction.Transactional;

@Repository
public interface SendMsgRepository extends JpaRepository<SendMsg, Long> {
	
}
