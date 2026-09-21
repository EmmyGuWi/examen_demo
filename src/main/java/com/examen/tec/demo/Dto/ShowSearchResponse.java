package com.examen.tec.demo.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data 
public class ShowSearchResponse {
    private Long id;
    private String name;
    private String channel;
    List<String> genres;
    private String summary;
}
