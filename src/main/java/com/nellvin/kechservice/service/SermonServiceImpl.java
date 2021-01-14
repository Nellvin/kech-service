package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Sermon;
import com.nellvin.kechservice.repository.SermonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SermonServiceImpl implements SermonService {

    private final String URL_BASE = "http://localhost:8080/api/sermon/";

    @Autowired
    private SermonRepository sermonRepository;

    @Override
    public List<Sermon> retrieveSermons() {
        List<Sermon> list = sermonRepository.findAll();
        Collections.sort(list, Comparator.comparing(Sermon::getDate));
        Collections.reverse(list);
        return list;
    }

    @Override
    public Sermon getSermon(Long sermonId) {
        return sermonRepository.findById(sermonId).get();
    }

    @Override
    public Sermon saveSermon(Sermon sermon) {
        sermonRepository.save(sermon);
        if(sermon.getPhotoUrl() == null) {
            sermon.setPhotoUrl(URL_BASE + sermon.getId() + "/image");
            sermonRepository.save(sermon);
        }
        return sermon;
    }

    @Override
    public void deleteSermon(Long sermonId) {
        sermonRepository.deleteById(sermonId);
    }

    @Override
    public void updateSermon(Sermon sermon) {
        if(sermon.getPhotoUrl() == null) {
            sermon.setPhotoUrl(URL_BASE + sermon.getId() + "/image");
            sermonRepository.save(sermon);
        }
        sermonRepository.save(sermon);

    }
}
