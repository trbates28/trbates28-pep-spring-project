package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.entity.*;
import org.springframework.data.jpa.*;
import java.lang.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


public interface MessageRepository extends JpaRepository<Message, Long>  {

    List<Message> findAll();

    @Query("SELECT m FROM Message m WHERE m.messageId = :messageId")
    Message findById(@Param("messageId") int messageId);

    @Query("SELECT m FROM Message m WHERE m.postedBy = :postedBy")
    List<Message>getAllByAccount(@Param("postedBy") int postedBy);

    @Query("SELECT MIN(m.messageId) FROM Message m WHERE m.postedBy = :postedBy")
    int postedBy(@Param("postedBy") int postedBy);
    
    @Modifying
    @Query(value = "insert into message (postedBy, messageText, timePostedEpoch) VALUES (:postedBy,:messageText,:timePostedEpoch)", nativeQuery = true)
    @Transactional
    void insertNewMessage(@Param("postedBy") int postedBy, @Param("messageText") String messageText, @Param("timePostedEpoch") Long timePostedEpoch);
  
    @Modifying
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    @Transactional
    void patchMessageById(@Param("messageText") String messageText, @Param("messageId") int messageId);

    @Modifying
    @Query("DELETE FROM Message m WHERE m.messageId = :messageId")
    @Transactional
    void deleteById(@Param("messageId") int messageId);

}
