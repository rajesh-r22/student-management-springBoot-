package com.example.student_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 100,unique = true)
    private String email;

    private Integer age;

}
