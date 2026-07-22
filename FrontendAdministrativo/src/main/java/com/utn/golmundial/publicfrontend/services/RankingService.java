package com.utn.golmundial.publicfrontend.services;

import com.utn.golmundial.publicfrontend.model.RankingEntry;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

public class RankingService {

    private static final String BASE_URL = "http://localhost:5253";

    public List<RankingEntry> obtenerRanking() {
        Client client = ClientBuilder.newClient();
        try {
            Response response = client.target(BASE_URL)
                    .path("/api/ranking")
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            if (response.getStatus() == 200) {
                return response.readEntity(new GenericType<List<RankingEntry>>() {});
            } else {
                return List.of();
            }
        } finally {
            client.close();
        }
    }
}