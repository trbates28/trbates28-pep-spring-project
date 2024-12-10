package com.example.repository;
import java.util.List;
import com.example.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends JpaRepository<Account, Long>  {

    @Query("SELECT a FROM Account a WHERE a.accountId = :accountId")
    Account findById(@Param("accountId") int accountId);

    @Query("SELECT a FROM Account a WHERE a.username = :username")
    Account findByUser(@Param("username") String username); 

    @Modifying
    @Query(value = "insert into Account (username, password) VALUES (:username,:password)", nativeQuery = true)
    @Transactional
    void insertNewAccount(@Param("username") String username, @Param("password") String password);

    @Query("SELECT MIN(a.accountId) FROM Account a WHERE a.username = :username")
    int postedBy(@Param("username") String username); 

   
}
