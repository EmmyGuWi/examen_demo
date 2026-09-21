package com.examen.tec.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examen.tec.demo.Client.ApiClient;
import com.examen.tec.demo.Dto.ShowResponse;
import com.examen.tec.demo.Model.ShowMapper;

@Service
public class ShowServiceImpl implements ShowService {

  private final ApiClient tvMazeClient;
    private final ShowMapper showMapper;

    public ShowServiceImpl(
            ApiClient tvMazeClient,
            ShowMapper showMapper) {

        this.tvMazeClient = tvMazeClient;
        this.showMapper = showMapper;
    }

    @Override
    public ShowResponse getShow(Long showId) {

        var show = tvMazeClient.getShow(showId);

        return showMapper.toShowResponse(show);
    }


}
