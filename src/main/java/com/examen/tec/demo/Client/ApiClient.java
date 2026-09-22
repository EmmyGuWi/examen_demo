package com.examen.tec.demo.Client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.examen.tec.demo.Model.TvMazeSearchResult;
import com.examen.tec.demo.Model.TvMazeShow;
@Component

public class ApiClient {

    private final RestClient restClient;

    public ApiClient(RestClient restClient) {
        this.restClient = restClient;
    }

     public List<TvMazeSearchResult> searchShows(String query) {

        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/shows")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
       public TvMazeShow getShow(Long showId) {

        try {
            return restClient.get()
                    .uri("/shows/{id}", showId)
                    .retrieve()
                    .body(TvMazeShow.class);
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        }
    }

}
