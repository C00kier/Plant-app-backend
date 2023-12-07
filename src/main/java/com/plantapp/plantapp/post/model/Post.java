package com.plantapp.plantapp.post.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "text")
    private String title;
    @Column(columnDefinition = "text")
    private String lead;
    @Column(columnDefinition = "text")
    private String article;
    @Column(columnDefinition = "date")
    private LocalDate date;
}
