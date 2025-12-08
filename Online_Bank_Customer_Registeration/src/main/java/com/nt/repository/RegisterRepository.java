package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.RegisterUser;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterUser, Integer> {
	 boolean existsByEmail(String email);
}
