package com.examen.tec.demo.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examen.tec.demo.Dto.CommentRequest;
import com.examen.tec.demo.Dto.ShowResponse;
import com.examen.tec.demo.Dto.ShowSearchResponse;
import com.examen.tec.demo.Service.CommentService;
import com.examen.tec.demo.Service.SearchService;
import com.examen.tec.demo.Service.ShowService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shows")
public class SearchController {
  
    private final SearchService searchService;
    private final ShowService showService;
    private final CommentService commentService;

    public SearchController(SearchService searchService, ShowService showService, CommentService commentService) {
        this.searchService = searchService;
        this.showService = showService;
        this.commentService = commentService;
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

   @PostMapping("/addcomment")
 public ResponseEntity<Void> addComment(
        @Valid @RequestBody CommentRequest request) {
        commentService.saveComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
