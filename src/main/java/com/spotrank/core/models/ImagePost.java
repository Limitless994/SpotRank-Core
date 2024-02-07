package com.spotrank.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("POST")
public class ImagePost extends Image {

    @OneToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

}