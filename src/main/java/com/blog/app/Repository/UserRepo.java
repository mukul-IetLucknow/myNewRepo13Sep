package com.blog.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.Entities.User;

public interface UserRepo extends JpaRepository<User,Integer>  {

}
