package com.myresume.dao;

import com.myresume.entity.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Short> {
    List<SkillCategory> findAllByOrderByIdAsc();
}
