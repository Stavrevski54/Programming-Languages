package com.example.studentgroupapi.repository;

import com.example.studentgroupapi.model.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {
}
