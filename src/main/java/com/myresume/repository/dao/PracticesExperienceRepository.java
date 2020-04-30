package com.myresume.repository.dao;

import com.myresume.entity.Practic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticesExperienceRepository extends JpaRepository<Practic, Long> {
}
