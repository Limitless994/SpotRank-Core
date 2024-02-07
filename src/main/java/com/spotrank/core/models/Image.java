package com.spotrank.core.models;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "file", unique = false, nullable = false, length = 100000)
    private byte[] file;

    @Column(name = "entity_type", insertable = false, updatable = false)
    private String entityType;

}