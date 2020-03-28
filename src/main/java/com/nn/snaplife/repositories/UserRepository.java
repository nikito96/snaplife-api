package com.nn.snaplife.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nn.snaplife.beans.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
