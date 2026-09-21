package com.examen.tec.demo.Service;

import org.springframework.stereotype.Service;

import com.examen.tec.demo.Dto.CommentRequest;
import com.examen.tec.demo.Entities.CommentDocument;
import com.examen.tec.demo.Repository.CommentRepository;
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void saveComment(CommentRequest request) {
          CommentDocument document = new CommentDocument();

        document.setShowId(request.getShowId());
        document.setComment(request.getComment());
        document.setRating(request.getRating());

        commentRepository.save(document);
      
    }

}
