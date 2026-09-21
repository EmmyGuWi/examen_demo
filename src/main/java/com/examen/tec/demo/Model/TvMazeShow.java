package com.examen.tec.demo.Model;

import java.util.List;

import lombok.Data;

@Data
public class TvMazeShow {

       private  Long id;
       private  String name;
       private  String summary;
       private List<String> genres;
       private TvMazeNetwork network;
       private TvMazeWebChannel webChannel;
}
