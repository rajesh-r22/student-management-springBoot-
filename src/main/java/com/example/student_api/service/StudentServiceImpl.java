package com.example.student_api.service;

import com.example.student_api.dto.PagedResponse;
import com.example.student_api.dto.StudentDto;
import com.example.student_api.dto.StudentRankDto;
import com.example.student_api.entity.Student;
import com.example.student_api.exception.DuplicateResourceException;
import com.example.student_api.exception.InvalidRequestException;
import com.example.student_api.exception.ResourceNotFoundException;
import com.example.student_api.mapper.StudentMapper;
import com.example.student_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor   // Lombok generates constructor for final fields -> constructor injection
public class StudentServiceImpl implements StudentService {

    private static final Logger log=LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {

        // Business validation — lives in the Service, because it needs DB access + domain knowledge
        // business rule #1: age restriction
        if(studentDto.getAge()<18){
            throw new InvalidRequestException("Student age is < 18 ! access denied");
        }

        // business rule #2: no duplicate emails — delegated to repository, not looped in Java
        if(studentRepository.existsByEmail(studentDto.getEmail())){
            throw new DuplicateResourceException("Student email already registered "+studentDto.getEmail());
        }

        Student student = studentMapper.toEntity(studentDto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDTO(savedStudent);

    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id "+id));
        return studentMapper.toDTO(student);
    }

    @Override
    public PagedResponse<StudentDto> getAllStudents(int page, int size , String sortBy, String direction){
//    Whitelist allowed sort fields
        Set<String> allowedSortFields = Set.of("id","name","email","age");
        if(!allowedSortFields.contains(sortBy)){
            throw new InvalidRequestException("Sort fields are not allowed "+sortBy);
        }
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<Student> studentPage = studentRepository.findAll(pageable);

        List<StudentDto> content= studentPage.getContent()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
        return new PagedResponse<>(
                content,
                studentPage.getNumber(),
                studentPage.getSize(),
                studentPage.getTotalPages(),
                studentPage.isLast()
        );
    }

    @Override
    public StudentDto updateStudentById(Long id, StudentDto studentDto) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with  Id  "+ id+" not found "));
        existing.setName(studentDto.getName());
        existing.setAge(studentDto.getAge());
        existing.setEmail(studentDto.getEmail());
        studentRepository.save(existing);
        return studentMapper.toDTO(existing);
    }

    @Override
    public void deleteStudentById(Long id){
        if(!studentRepository.existsById(id)){
            throw new ResourceNotFoundException("Student with  Id  "+ id+" not found ");
        }
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDto> getStudentByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentInAgeRange(Integer minAge, Integer maxAge){
        if (minAge>maxAge){
            throw new InvalidRequestException("minAge cannot be greater than maxAge "+maxAge);
        }
        return studentRepository.findStudentsInAgeRange(minAge,maxAge)
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentRankDto> getStudentsByAgeRank(){
        return studentRepository.findStudentsRankedBYAge()
                .stream()
                .map(p-> new StudentRankDto(p.getId(),p.getName(),p.getAge(),p.getRank()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StudentDto IncrementAge(Long id){
        Student updated=studentRepository.incrementAgeAndReturn(id);
        if(updated==null){
            throw new ResourceNotFoundException("Student with  Id  "+ id+" not found ");
        }
        return studentMapper.toDTO(updated);
    }

    @Override
    public List<StudentDto> getStudentsWithEmailNotificationsOn(){
        return studentRepository.findStudentWithEmailNotificationsOn()
                .stream()
                .map(studentMapper::toDTO)
                .collect(Collectors.toList());
    }





//  mapping helpers that helps us to convert dto to entity and entity to Dto
//    public Student toEntity(StudentDto dto) {
//        Student student = new Student();
//
//        student.setName(dto.getName());
//        student.setAge(dto.getAge());
//        student.setEmail(dto.getEmail());
//        return student;
//    }
//    public StudentDto toDto(Student student){
//        StudentDto studentDto = new StudentDto();
//        studentDto.setId(student.getId());
//        studentDto.setName(student.getName());
//        studentDto.setAge(student.getAge());
//        studentDto.setEmail(student.getEmail());
//        return studentDto;
//    }


}
