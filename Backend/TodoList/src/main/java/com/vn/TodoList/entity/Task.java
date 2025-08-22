package com.vn.TodoList.entity;

import java.sql.Date;
import java.util.Set;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(length = 500)

    String detail;
    @Column(length = 500)

    String title;
    Date timeStart;
    int timeEnd;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category categoryID;
    @Enumerated(EnumType.STRING)
    Priority priority;
    boolean status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User userID;
}
