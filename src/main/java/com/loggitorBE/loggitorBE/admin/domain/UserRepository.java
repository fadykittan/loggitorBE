package com.loggitorBE.loggitorBE.admin.domain;

import java.math.BigInteger;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <Users, Long> {

	
	@Query(value ="select u.user_id from users u where u.email = ?1",nativeQuery = true)
	ArrayList<BigInteger> findByUserName(String userName);
	
	
	/*
    @Query(nativeQuery=true)
   Set<UsersOnHP> getUsersOnHP();
    @Query(nativeQuery=true)
   Set<UserRoleArray> getUsersRoleArray(@Param("UserId") long UserId);
    @Query(nativeQuery=true)
   Set<UsersWithRole> getUsersWithRole();
    @Query(nativeQuery=true)
    Optional<Users> login(@Param("Email") String email,@Param("Password") String password);
*/
}