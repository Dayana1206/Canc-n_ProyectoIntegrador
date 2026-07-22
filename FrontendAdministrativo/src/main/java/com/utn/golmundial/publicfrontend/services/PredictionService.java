package com.utn.golmundial.publicfrontend.services;

import com.utn.golmundial.publicfrontend.model.Prediction;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

public class PredictionService {

    private static final String BASE_URL = "http://localhost:5253";

    public Prediction crearApuesta(Prediction datos) {
        Client client = ClientBuilder.newClient();
        try {
            Response response = client.target(BASE_URL)
                    .path("/api/predicciones")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(datos, MediaType.APPLICATION_JSON));

            if (response.getStatus() == 201) {
                return response.readEntity(Prediction.class);
            } else {
                return null;
            }
        } finally {
            client.close();
        }
    }

    public List<Prediction> obtenerApuestasUsuario(Long usuarioId) {
        Client client = ClientBuilder.newClient();
        try {
            Response response = client.target(BASE_URL)
                    .path("/api/predicciones/usuario/" + usuarioId)
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            if (response.getStatus() == 200) {
                return response.readEntity(new GenericType<List<Prediction>>() {});
            } else {
                return List.of();
            }
        } finally {
            client.close();
        }
    }
}