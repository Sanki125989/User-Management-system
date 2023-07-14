package com.user.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.user.management.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM User u WHERE u.email = :email and u.password = :password")
	List<User> findByEmailandPassword(@Param("email") String email,@Param("password") String password);

	@Query("SELECT COUNT(u)>1 FROM User u WHERE u.email = :email OR u.mobileno= :mobileno AND u.isactive = true")
	boolean existsByEmailAndMobileNumberIsActive(@Param("email") String email,@Param("mobileno") String mobileno);

	
    Optional<User> findById(Long id);

	@Query("SELECT u FROM User u WHERE u.isactive=true")
	List<User> getAllActiveUsers();
	
	@Query("SELECT u FROM User u WHERE u.isactive=false")
	List<User> getAllInActiveUsers();

	@Query("SELECT u FROM User u WHERE u.email=:email")
	User findByEmail(@Param("email") String email);


}
