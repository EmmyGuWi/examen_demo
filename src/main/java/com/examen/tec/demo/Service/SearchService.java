package com.examen.tec.demo.Service;

import java.util.List;

import com.examen.tec.demo.Dto.ShowSearchResponse;

public interface SearchService {
    public List<ShowSearchResponse> search(String query);

}
