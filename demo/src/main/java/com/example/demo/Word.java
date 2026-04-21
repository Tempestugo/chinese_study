package com.example.demo;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chinese_character")
    private String character;

    private String pinyin;
    private String meaning;
    private String exampleSentence;
    private LocalDate createdAt;
}