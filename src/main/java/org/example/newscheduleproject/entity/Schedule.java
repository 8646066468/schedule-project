package org.example.newscheduleproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Schedule(String name, String title, String content) {
        this.name = name;
        this.title = title;
        this.content = content;
    }

    public void updatesc(String content, String name, String title) {
        this.content = content;
        this.name = name;
        this.title = title;
    }
}

