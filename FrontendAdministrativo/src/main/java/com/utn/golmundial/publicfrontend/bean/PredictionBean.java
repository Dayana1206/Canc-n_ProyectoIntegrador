package com.utn.golmundial.publicfrontend.bean;

import com.utn.golmundial.publicfrontend.model.Prediction;
import com.utn.golmundial.publicfrontend.services.PredictionService;
import com.utn.golmundial.publicfrontend.services.PredictionServiceMock;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class PredictionBean implements Serializable {

    private static final boolean usarMock = true;

    private final PredictionService predictionService = new PredictionService();
    private final PredictionServiceMock predictionServiceMock = new PredictionServiceMock();

    // TODO: reemplaza por el id real del usuario logueado
    private Long usuarioId = 995L;

    private List<Prediction> misApuestas;

    // Campos del formulario para crear una nueva apuesta
    private Long partidoId;
    private String pronostico;
    private Double monto;

    @PostConstruct
    public void init() {
        cargarApuestas();
    }

    public void cargarApuestas() {
        if (usarMock) {
            misApuestas = predictionServiceMock.obtenerApuestasUsuario(usuarioId);
        } else {
            misApuestas = predictionService.obtenerApuestasUsuario(usuarioId);
        }
    }

    public void apostar() {
        Prediction nueva = new Prediction();
        nueva.setUsuarioId(usuarioId);
        nueva.setPartidoId(partidoId);
        nueva.setPronostico(pronostico);
        nueva.setMonto(monto);
        nueva.setFechaInicioPartido("2026-08-01T20:00:00Z"); // TODO: tomar la fecha real del partido elegido

        Prediction resultado;
        if (usarMock) {
            resultado = predictionServiceMock.crearApuesta(nueva);
        } else {
            resultado = predictionService.crearApuesta(nueva);
        }

        if (resultado != null) {
            cargarApuestas(); // refresca la lista para que se vea la nueva apuesta
        }
    }

    public List<Prediction> getMisApuestas() {
        return misApuestas;
    }

    public Long getPartidoId() { 
        return partidoId; 
    }
    public void setPartidoId(Long partidoId) { 
        this.partidoId = partidoId; 
    }
    public String getPronostico() { 
        return pronostico; 
    }
    public void setPronostico(String pronostico) { 
        this.pronostico = pronostico; 
    }
    public Double getMonto() { 
        return monto; 
    }
    public void setMonto(Double monto) { 
        this.monto = monto;
    }
}