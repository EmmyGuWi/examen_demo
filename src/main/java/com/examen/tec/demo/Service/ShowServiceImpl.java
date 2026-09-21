package com.examen.tec.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examen.tec.demo.Client.ApiClient;
import com.examen.tec.demo.Dto.ShowResponse;
import com.examen.tec.demo.Entities.ShowDocument;
import com.examen.tec.demo.Model.ShowMapper;
import com.examen.tec.demo.Repository.ShowRepository;

@Service
public class ShowServiceImpl implements ShowService {

    private final ApiClient tvMazeClient;
    private final ShowMapper showMapper;
    private final ShowRepository showRepository;

    public ShowServiceImpl(
            ApiClient tvMazeClient,
            ShowMapper showMapper, ShowRepository showRepository) {

        this.tvMazeClient = tvMazeClient;
        this.showMapper = showMapper;
        this.showRepository = showRepository;
    }

    @Override
    public ShowResponse getShow(Long showId) {

      /*   var show = tvMazeClient.getShow(showId);
        return showMapper.toShowResponse(show);*/

        Optional<ShowDocument> cachedShow = showRepository.findById(showId);

        if (cachedShow.isPresent()) {
            return showMapper.toShowResponse(cachedShow.get());
        }

        var show = tvMazeClient.getShow(showId);

        var document = showMapper.toDocument(show);

        showRepository.save(document);

        return showMapper.toShowResponse(document);
    }

}
