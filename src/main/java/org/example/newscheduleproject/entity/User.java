package org.example.newscheduleproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;


    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
