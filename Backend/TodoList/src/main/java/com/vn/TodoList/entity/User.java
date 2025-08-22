package com.vn.TodoList.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User {
    @Id
    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy = "userID")
    Set<Task> tasks;

    @OneToMany(mappedBy = "userID")
    Set<Category> categories;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    boolean active = false;
    String otp;
    LocalDateTime otpExpireTime;

}
