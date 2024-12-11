package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.example.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface MessageRepository extends JpaRepository<Message, Integer>  {

    List<Message> findByPostedBy(int postedBy);

    @Query("SELECT MIN(m.messageId) FROM Message m WHERE m.postedBy = :postedBy")
    int postedBy(@Param("postedBy") int postedBy);
    
}
