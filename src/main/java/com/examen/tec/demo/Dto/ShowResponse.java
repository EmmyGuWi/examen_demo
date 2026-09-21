package com.examen.tec.demo.Dto;

import java.util.List;

import lombok.Data;

@Data 
public class ShowResponse {
      private  Long id;
      private  String name;
      private  String channel;
      private  String summary;
      private  List<String> genres;
      private  List<CommentResponse> comments;
}
