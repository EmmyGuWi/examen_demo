package com.examen.tec.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examen.tec.demo.Dto.ShowResponse;
import com.examen.tec.demo.Dto.ShowSearchResponse;
import com.examen.tec.demo.Service.SearchService;
import com.examen.tec.demo.Service.ShowService;

@RestController
@RequestMapping("/shows")
public class SearchController {
  
    private final SearchService searchService;
    private final ShowService showService;

    public SearchController(SearchService searchService, ShowService showService) {
        this.searchService = searchService;
        this.showService = showService;
    }

    @GetMapping("/search")
    public List<ShowSearchResponse> search(
            @RequestParam String query) {

        return searchService.search(query);
    }

    @GetMapping("/{showId}")
    public ShowResponse getShow(
            @PathVariable Long showId) {

        return showService.getShow(showId);
    }

}
