package com.utn.golmundial.publicfrontend.bean;

import com.utn.golmundial.publicfrontend.model.GroupStanding;
import com.utn.golmundial.publicfrontend.services.StandingsService;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Named
@ViewScoped
public class StandingsBean implements Serializable {

    @Inject
    private StandingsService standingsService;

    private Map<String, List<GroupStanding>> standingsByGroup;

    @PostConstruct
    public void init() {
        standingsByGroup = standingsService.computeGroupStandings();
    }

    // Lista de nombres de grupo ("A", "B", "C"...) ya ordenada,
    // para que la página sepa cuántas pestañas/tablas dibujar.
    public List<String> getGroupKeys() {
        return new java.util.ArrayList<>(new TreeSet<>(standingsByGroup.keySet()));
    }

    public Map<String, List<GroupStanding>> getStandingsByGroup() {
        return standingsByGroup;
    }
}