package com.database.InternalDB.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.database.InternalDB.entity.Users;

@Repository
public interface UsersRespository extends JpaRepository<Users,Long> {
	
	 @Query(value = "SELECT * FROM users WHERE username = :username AND password = :password", nativeQuery = true)
	    Optional<Users> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
