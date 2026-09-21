package com.examen.tec.demo.Entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "shows")
@Data
public class ShowDocument {

    @Id
    private Long id;
    private String name;
    private String channel;
    private String summary;
    private List<String> genres;

}
