package com.spotrank.core.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date creationDate;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private User admin;

    @ManyToMany
    private Set<User> members = new HashSet<>(0);

    }
