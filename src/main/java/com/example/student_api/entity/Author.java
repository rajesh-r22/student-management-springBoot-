package com.example.student_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="authors")
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // INVERSE side — "mappedBy" says: "the Book entity's 'author' field owns this relationship"
    // Author does NOT have a foreign key column — it just knows how to LOOK UP its books
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    // CRITICAL: @Data's generated toString()/equals()/hashCode() would recurse into books,
    // and each Book's toString() would recurse back into author -> infinite loop -> StackOverflowError.
    // We override manually to break the cycle:
    @Override
    public String toString() {
        return "Author{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }

}
