package com.examen.tec.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.examen.tec.demo.Entities.ShowDocument;

@Repository
public interface ShowRepository extends MongoRepository<ShowDocument, Long> {

}
