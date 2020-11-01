package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Sermon;
import com.nellvin.kechservice.repository.SermonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SermonServiceImpl implements SermonService{

    @Autowired
    private SermonRepository sermonRepository;

    @Override
    public List<Sermon> retrieveSermons() {
        return sermonRepository.findAll();
    }

    @Override
    public Sermon getSermon(Long sermonId) {
        return sermonRepository.findById(sermonId).get();
    }

    @Override
    public Sermon saveSermon(Sermon sermon) {
        sermonRepository.save(sermon);
        return sermon;
    }

    @Override
    public void deleteSermon(Long sermonId) {
        sermonRepository.deleteById(sermonId);
    }

    @Override
    public void updateSermon(Sermon sermon) {
        sermonRepository.save(sermon);
    }
}
