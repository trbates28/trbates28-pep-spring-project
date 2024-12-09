package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.entity.*;
import org.springframework.data.jpa.*;
import java.lang.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MessageRepository extends JpaRepository<Message, Long>  {

    List<Message> findAll();

}
