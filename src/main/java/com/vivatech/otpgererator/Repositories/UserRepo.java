package com.vivatech.otpgererator.Repositories;

import com.vivatech.otpgererator.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

   @Query( value = "select * from User where email = :email",nativeQuery = true)
    User findByEmail(String email);
}
