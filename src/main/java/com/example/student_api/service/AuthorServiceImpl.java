package com.example.student_api.service;

import com.example.student_api.dto.AuthorDetailsDto;
import com.example.student_api.dto.BookDto;
import com.example.student_api.entity.Author;
import com.example.student_api.entity.Book;
import com.example.student_api.exception.ResourceNotFoundException;
import com.example.student_api.mapper.AuthorMapper;
import com.example.student_api.mapper.BookMapper;
import com.example.student_api.repository.AuthorRepository;
import com.example.student_api.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    @Override
    @Transactional// needed because we access author.getBooks() (LAZY) within this method
    public AuthorDetailsDto getAuthorWithBooks(Long authorId) {
        Author author =authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + authorId));
        log.info("fetched author {} with {} books", author.getName(), author.getBooks().size());
        return authorMapper.toDetailsDto(author);
    }

    @Override
    @Transactional
    public BookDto addBookToAuthor(Long authorId, BookDto bookDto) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + authorId));
        Book book= new Book();
        book.setTitle(bookDto.getTitle());
        book.setIsbn(bookDto.getIsbn());
        book.setAuthor(author);
        log.info("fetched book {} with {} books", book.getTitle(), book.getIsbn());

        Book saved=bookRepository.save(book);
        return bookMapper.toDto(saved);
    }


}
