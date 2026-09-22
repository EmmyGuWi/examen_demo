package com.examen.tec.demo.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.examen.tec.demo.Client.ApiClient;
import com.examen.tec.demo.Dto.CommentResponse;
import com.examen.tec.demo.Dto.ShowResponse;
import com.examen.tec.demo.Entities.CommentDocument;
import com.examen.tec.demo.Entities.ShowDocument;
import com.examen.tec.demo.Exceptions.GeneralException;
import com.examen.tec.demo.Model.ShowMapper;
import com.examen.tec.demo.Model.TvMazeShow;
import com.examen.tec.demo.Repository.CommentRepository;
import com.examen.tec.demo.Repository.ShowRepository;


@ExtendWith(MockitoExtension.class)
public class ShowServiceImplTest {


    @Mock
    private ApiClient tvMazeClient;

    @Mock
    private ShowMapper showMapper;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private ShowServiceImpl showService;



    @Test
void shouldReturnCachedShowWithComments() {

    Long showId = 139L;

    ShowDocument document = new ShowDocument();
    document.setId(showId);
    document.setName("Girls");
    document.setChannel("HBO");

    ShowResponse response = new ShowResponse();
    response.setId(showId);
    response.setName("Girls");
    response.setChannel("HBO");

    CommentDocument comment = new CommentDocument();
    comment.setShowId(showId);
    comment.setComment("Excelente serie");
    comment.setRating(5);

    CommentResponse commentResponse = new CommentResponse();
    commentResponse.setComment("Excelente serie");
    commentResponse.setRating(5);

    when(showRepository.findById(showId))
            .thenReturn(Optional.of(document));

    when(showMapper.toShowResponse(document))
            .thenReturn(response);

    when(commentRepository.findByShowId(showId))
            .thenReturn(List.of(comment));

    when(showMapper.toCommentResponse(comment))
            .thenReturn(commentResponse);

    ShowResponse result = showService.getShow(showId);

    assertNotNull(result);
    assertEquals(139L, result.getId());
    assertEquals("Girls", result.getName());
    assertEquals(1, result.getComments().size());
    assertEquals("Excelente serie",
            result.getComments().get(0).getComment());
    assertEquals(5,
            result.getComments().get(0).getRating());

    verify(tvMazeClient, never()).getShow(showId);
}

@Test
void shouldGetShowFromApiAndSaveItWhenNotCached() {

    Long showId = 139L;

    TvMazeShow show = new TvMazeShow();
    show.setId(showId);
    show.setName("Girls");

    ShowDocument document = new ShowDocument();
    document.setId(showId);
    document.setName("Girls");

    ShowResponse response = new ShowResponse();
    response.setId(showId);
    response.setName("Girls");

    when(showRepository.findById(showId))
            .thenReturn(Optional.empty());

    when(tvMazeClient.getShow(showId))
            .thenReturn(show);

    when(showMapper.toDocument(show))
            .thenReturn(document);

    when(showMapper.toShowResponse(document))
            .thenReturn(response);

    when(commentRepository.findByShowId(showId))
            .thenReturn(List.of());

    ShowResponse result = showService.getShow(showId);

    assertNotNull(result);
    assertEquals(showId, result.getId());
    assertEquals("Girls", result.getName());

    verify(tvMazeClient).getShow(showId);
    verify(showRepository).save(document);
}

@Test
void shouldReturnAllCommentsForShow() {

    Long showId = 139L;

    ShowDocument document = new ShowDocument();
    document.setId(showId);

    ShowResponse response = new ShowResponse();
    response.setId(showId);

    CommentDocument comment1 = new CommentDocument();
    comment1.setShowId(showId);
    comment1.setComment("Excelente serie");
    comment1.setRating(5);

    CommentDocument comment2 = new CommentDocument();
    comment2.setShowId(showId);
    comment2.setComment("Excelente serie 2");
    comment2.setRating(5);

    CommentResponse response1 = new CommentResponse();
    response1.setComment("Excelente serie");
    response1.setRating(5);

    CommentResponse response2 = new CommentResponse();
    response2.setComment("Excelente serie 2");
    response2.setRating(5);

    when(showRepository.findById(showId))
            .thenReturn(Optional.of(document));

    when(showMapper.toShowResponse(document))
            .thenReturn(response);

    when(commentRepository.findByShowId(showId))
            .thenReturn(List.of(comment1, comment2));

    when(showMapper.toCommentResponse(comment1))
            .thenReturn(response1);

    when(showMapper.toCommentResponse(comment2))
            .thenReturn(response2);

    ShowResponse result = showService.getShow(showId);

    assertEquals(2, result.getComments().size());

    assertEquals("Excelente serie",
            result.getComments().get(0).getComment());

    assertEquals("Excelente serie 2",
            result.getComments().get(1).getComment());
}
@Test
void shouldThrowExceptionWhenShowDoesNotExist() {

    Long showId = 999999L;

    when(showRepository.findById(showId))
            .thenReturn(Optional.empty());

    when(tvMazeClient.getShow(showId))
            .thenReturn(null);

    assertThrows(
            GeneralException.class,
            () -> showService.getShow(showId)
    );

    verify(showRepository, never()).save(any());
}
}
