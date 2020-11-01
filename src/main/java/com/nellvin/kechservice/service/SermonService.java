package com.nellvin.kechservice.service;

import com.nellvin.kechservice.model.Sermon;

import java.util.List;

public interface SermonService {
    public List<Sermon> retrieveSermons();

    public Sermon getSermon(Long sermonId);

    public Sermon saveSermon(Sermon sermon);

    public void deleteSermon(Long sermonId);

    public void updateSermon(Sermon sermon);
}