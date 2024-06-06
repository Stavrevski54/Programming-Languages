package com.example.studentgroupapi.controller;

import com.example.studentgroupapi.model.StudentGroup;
import com.example.studentgroupapi.service.StudentGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentgroups")
public class StudentGroupController {

    @Autowired
    private StudentGroupService studentGroupService;

    @PostMapping
    public ResponseEntity<StudentGroup> createStudentGroup(@RequestBody StudentGroup studentGroup) {
        return ResponseEntity.ok(studentGroupService.createStudentGroup(studentGroup));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentGroup> updateStudentGroup(@PathVariable Long id, @RequestBody StudentGroup studentGroup) {
        return ResponseEntity.ok(studentGroupService.updateStudentGroup(id, studentGroup));
    }

    @GetMapping
    public ResponseEntity<List<StudentGroup>> getAllStudentGroups() {
        return ResponseEntity.ok(studentGroupService.getAllStudentGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentGroup> getStudentGroupById(@PathVariable Long id) {
        return studentGroupService.getStudentGroupById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentGroup(@PathVariable Long id) {
        studentGroupService.deleteStudentGroup(id);
        return ResponseEntity.noContent().build();
    }
}
