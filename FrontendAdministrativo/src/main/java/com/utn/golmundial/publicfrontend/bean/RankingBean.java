package com.utn.golmundial.publicfrontend.bean;

import com.utn.golmundial.publicfrontend.model.RankingEntry;
import com.utn.golmundial.publicfrontend.services.RankingService;
import com.utn.golmundial.publicfrontend.services.RankingServiceMock;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RankingBean implements Serializable {

    private static final boolean usarMock = true;

    private final RankingService rankingService = new RankingService();
    private final RankingServiceMock rankingServiceMock = new RankingServiceMock();

    private List<RankingEntry> ranking;

    @PostConstruct
    public void init() {
        if (usarMock) {
            ranking = rankingServiceMock.obtenerRanking();
        } else {
            ranking = rankingService.obtenerRanking();
        }
    }

    public List<RankingEntry> getRanking() {
        return ranking;
    }
}