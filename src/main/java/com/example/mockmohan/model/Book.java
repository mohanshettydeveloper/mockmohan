package com.example.mockmohan.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="book_record")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @NonNull
    private String name;

    @NonNull
    private String summary;

    private int rating;

}
