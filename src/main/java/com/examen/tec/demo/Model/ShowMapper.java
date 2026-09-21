package com.examen.tec.demo.Model;

import org.springframework.stereotype.Component;

import com.examen.tec.demo.Dto.ShowResponse;
import com.examen.tec.demo.Dto.ShowSearchResponse;

@Component
public class ShowMapper {

     public ShowSearchResponse toSearchResponse(TvMazeSearchResult result) {

        TvMazeShow show = result.getShow();

        return new ShowSearchResponse(
                show.getId(),
                show.getName(),
                resolveChannel(show),
                show.getGenres(),
                show.getSummary()
        );
    }
     public ShowResponse toShowResponse(TvMazeShow show) {

        ShowResponse response = new ShowResponse();

        response.setId(show.getId());
        response.setName(show.getName());
        response.setChannel(resolveChannel(show));
        response.setSummary(show.getSummary());
        response.setGenres(show.getGenres());

        return response;
    }
    private String resolveChannel(TvMazeShow show) {

        if (show.getNetwork() != null) {
            return show.getNetwork().getName();
        }

        if (show.getWebChannel() != null) {
            return show.getWebChannel().getName();
        }

        return null;
    }

}
