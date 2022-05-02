package com.example.myspecificationproject.repository;

import com.example.myspecificationproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorySpec extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("select something from User something where something.name = 'stupid'")
    void doSomethingStupid();
}
