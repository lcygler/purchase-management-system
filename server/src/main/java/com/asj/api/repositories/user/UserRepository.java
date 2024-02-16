package com.asj.api.repositories.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.asj.api.models.user.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

	boolean existsById(Integer id);
	
	boolean existsByDni(String dni);

	boolean existsByDniAndIdNot(String dni, Integer id);

	boolean existsByEmail(String email);

	boolean existsByEmailAndIdNot(String email, Integer id);
	
	Optional<UserModel> findByEmail(String email);
	
	@Query(value = "SELECT COUNT(*) FROM users u WHERE u.genre_id = :id", nativeQuery = true)
	int countByGenre(@Param("id") Integer id);
	
	@Query(value = "SELECT COUNT(*) FROM users u WHERE u.role_id = :id", nativeQuery = true)
	int countByRole(@Param("id") Integer id);
	
	@Query(value = "SELECT COUNT(*) FROM users u WHERE u.address_id = :id", nativeQuery = true)
	int countByAddress(@Param("id") Integer id);
}
