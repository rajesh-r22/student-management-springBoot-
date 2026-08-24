package com.example.student_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;

    // OWNING side — this is what generates the actual `author_id` FOREIGN KEY column
    @ManyToOne(fetch = FetchType.LAZY)   // LAZY is critical here — explained in section 3.5 and Q15
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Override
    public String toString(){
        return "Book{id=" + id + ", title='" + title + "', isbn='" + isbn + "'}";
    }

}
