package com.example.studentgroupapi.service;

import com.example.studentgroupapi.model.StudentGroup;
import com.example.studentgroupapi.repository.StudentGroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentGroupService {

    @Autowired
    private StudentGroupRepository studentGroupRepository;

    @Transactional
    public StudentGroup createStudentGroup(StudentGroup studentGroup) {
        return studentGroupRepository.save(studentGroup);
    }

    @Transactional
    public StudentGroup updateStudentGroup(Long id, StudentGroup studentGroupDetails) {
        StudentGroup studentGroup = studentGroupRepository.findById(id).orElseThrow(() -> new RuntimeException("StudentGroup not found"));
        studentGroup.setName(studentGroupDetails.getName());
        return studentGroupRepository.save(studentGroup);
    }

    public List<StudentGroup> getAllStudentGroups() {
        return studentGroupRepository.findAll();
    }

    public Optional<StudentGroup> getStudentGroupById(Long id) {
        return studentGroupRepository.findById(id);
    }

    @Transactional
    public void deleteStudentGroup(Long id) {
        studentGroupRepository.deleteById(id);
    }
}
