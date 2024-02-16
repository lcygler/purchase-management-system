package com.asj.api.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.user.UserCredentialModel;

public interface UserCredentialRepository extends JpaRepository<UserCredentialModel, Integer> {

	UserCredentialModel findByUserId(Integer userId);
	
	@Query(value = "SELECT COUNT(*) FROM user_credentials uc WHERE uc.user_id = :id", nativeQuery = true)
	int countByUser(@Param("id") Integer id);
}
