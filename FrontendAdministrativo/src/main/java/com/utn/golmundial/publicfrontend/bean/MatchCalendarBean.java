package com.utn.golmundial.publicfrontend.bean;

import com.utn.golmundial.publicfrontend.model.Match;
import com.utn.golmundial.publicfrontend.services.MatchService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MatchCalendarBean implements Serializable {

    @Inject
    private MatchService matchService;

    private List<Match> matches;

    @PostConstruct
    public void init() {
        matches = matchService.listGroupStageMatches();
    }

    public List<Match> getMatches() {
        return matches;
    }
}