package com.myresume.repository.dao;

import com.myresume.entity.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}
