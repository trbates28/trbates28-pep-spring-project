package com.example.repository;
import java.util.List;
import com.example.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long>  {

    @Query("SELECT a FROM Account a WHERE a.accountId = :accountId")
    Account findById(@Param("accountId") int accountId);

}
