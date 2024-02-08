package com.spotrank.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "file", unique = false, nullable = false, length = 100000)
    private byte[] file;

    @Column(name = "parentId")
    private Long parentId;

    @Column(name = "parentType")
    private ImageParentType parentType;

}