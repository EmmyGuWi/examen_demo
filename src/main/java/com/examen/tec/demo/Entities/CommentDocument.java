package com.examen.tec.demo.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "comments")
public class CommentDocument {
    @Id
    private String id;
    private Long showId;
    private String comment;
    private Integer rating;
}
