package com.examen.tec.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examen.tec.demo.Client.ApiClient;
import com.examen.tec.demo.Dto.ShowSearchResponse;
import com.examen.tec.demo.Model.ShowMapper;



@Service
public class SearchServiceImpl implements SearchService{
      private final ApiClient tvMazeClient;
        private final ShowMapper showMapper;

    public SearchServiceImpl(ApiClient tvMazeClient, ShowMapper showMapper) {
        this.tvMazeClient = tvMazeClient;
        this.showMapper = showMapper;
    }

    @Override
    public List<ShowSearchResponse> search(String query) {
    var results = tvMazeClient.searchShows(query);

        return results.stream()
                .map(showMapper::toSearchResponse)
                .toList();
    }


}
