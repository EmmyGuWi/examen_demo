package com.examen.tec.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.examen.tec.demo.Entities.CommentDocument;

public interface CommentRepository extends MongoRepository<CommentDocument, String>{

}
