package com.hr.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.ecom.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
