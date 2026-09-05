package com.example.student_api.student.repository;

import com.example.student_api.student.dto.StudentRankProjection;
import com.example.student_api.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository already gives us save(), findById(), findAll(), deleteById()

    // Spring Data JPA auto-generates: SELECT EXISTS(... WHERE email = ?)
    boolean existsByEmail(String email);

    // 1. Partial, case-insensitive name search
    List<Student> findByNameContainingIgnoreCase(String name);


    // 2. Age greater than a threshold
    List<Student> findByAgeGreaterThan(Integer age);

    // 3. Age range + sorting — method name gets long, but still derivable
    List<Student> findByAgeBetweenOrderByNameAsc(Integer minAge, Integer maxAge);

    // 4. Combine two conditions with AND
    List<Student> findByNameContainingIgnoreCaseAndAgeGreaterThan(String name, Integer age);

//    -------------------------------------------------------------------------------------------------------------------------

    // JPQL: operates on ENTITY names and FIELD names, not table/column names
    @Query("SELECT s FROM Student s WHERE s.email LIKE %:domain%")
    List<Student> findByEmailDomain(@Param("domain") String domain);

    // JPQL with multiple conditions — more readable than a long derived method name
    @Query("SELECT s FROM Student s WHERE s.age BETWEEN :minAge AND :maxAge ORDER BY s.name")
    List<Student> findStudentsInAgeRange(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);

    // JPQL aggregate query
    @Query("SELECT COUNT(s) FROM Student s WHERE s.age >= :adultAge")
    long countAdultStudents(@Param("adultAge") Integer adultAge);

    // JPQL projection — return only specific fields, not the whole entity
    @Query("SELECT s.name FROM Student s WHERE s.age > :age")
    List<String> findNamesOfStudentsOlderThan(@Param("age") Integer age);

    //    ----NATIVE QUERY--
    @Query(value = """
            SELECT s.id AS id, s.name AS name, s.age AS age,
            RANK() OVER (ORDER BY s.age DESC) AS age_rank
            FROM students s
    """, nativeQuery = true)
    List<StudentRankProjection> findStudentsRankedBYAge();


    @Query(value = """
    UPDATE students SET age = age + 1
    WHERE id = :id
    RETURNING *
    """, nativeQuery = true)
    Student incrementAgeAndReturn(@Param("id") Long id);

    @Query(value = """
    SELECT * FROM students
    WHERE preferences ->> 'notifyByEmail'='true' 
        """ , nativeQuery = true)
    List<Student> findStudentWithEmailNotificationsOn();

}
