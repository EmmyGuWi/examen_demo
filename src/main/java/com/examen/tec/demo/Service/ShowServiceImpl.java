package com.examen.tec.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examen.tec.demo.Client.ApiClient;
import com.examen.tec.demo.Dto.ShowResponse;
import com.examen.tec.demo.Entities.ShowDocument;
import com.examen.tec.demo.Exceptions.GeneralException;
import com.examen.tec.demo.Model.ShowMapper;
import com.examen.tec.demo.Repository.CommentRepository;
import com.examen.tec.demo.Repository.ShowRepository;

@Service
public class ShowServiceImpl implements ShowService {

    private final ApiClient tvMazeClient;
    private final ShowMapper showMapper;
    private final ShowRepository showRepository;
    private final CommentRepository commentRepository;

    public ShowServiceImpl(
            ApiClient tvMazeClient,
            ShowMapper showMapper, ShowRepository showRepository, CommentRepository commentRepository   ) {

        this.tvMazeClient = tvMazeClient;
        this.showMapper = showMapper;
        this.showRepository = showRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public ShowResponse getShow(Long showId) {

      /*   var show = tvMazeClient.getShow(showId);
        return showMapper.toShowResponse(show);*/

        Optional<ShowDocument> cachedShow = showRepository.findById(showId);
        ShowResponse response;
        if (cachedShow.isPresent()) {
           // return showMapper.toShowResponse(cachedShow.get());
            response = showMapper.toShowResponse(cachedShow.get());
        }else {

            var show = tvMazeClient.getShow(showId);
            if (show == null) {     
                     throw new GeneralException("No se encontró el show con id: " + showId);
            }

            var document = showMapper.toDocument(show);

            showRepository.save(document);

            response = showMapper.toShowResponse(document);
        }
        var comments = commentRepository.findByShowId(showId);
      
        var commentResponses = comments.stream()
                .map(showMapper::toCommentResponse)
                .toList();

        response.setComments(commentResponses);

        return response;
    }

}
