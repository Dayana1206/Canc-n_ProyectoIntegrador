package com.utn.golmundial.publicfrontend.bean;

import com.utn.golmundial.publicfrontend.model.Prediction;
import com.utn.golmundial.publicfrontend.services.PredictionService;
import com.utn.golmundial.publicfrontend.services.PredictionServiceMock;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@ViewScoped
public class PredictionBean implements Serializable {

    private static final boolean usarMock = false;

    private final PredictionService predictionService = new PredictionService();
    private final PredictionServiceMock predictionServiceMock = new PredictionServiceMock();

    // TODO: reemplaza por el id real del usuario logueado
    private Long usuarioId = 101L;

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

        // Validación mínima antes de llamar a la API (evita mandar campos vacíos)
        if (partidoId == null || pronostico == null || pronostico.isBlank() || monto == null || monto <= 0) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Datos incompletos",
                            "Completa el ID del partido, el resultado y un monto mayor a 0."));
            return;
        }

        Prediction nueva = new Prediction();
        nueva.setUsuarioId(usuarioId);
        nueva.setPartidoId(partidoId);
        nueva.setPronostico(pronostico);
        nueva.setMonto(monto);

        // TODO: esto NO debe ser un valor fijo. La API valida que el partido no haya
        // cerrado por hora comparando esta fecha con la hora actual (RF17), así que
        // aquí debe ir la fecha REAL de inicio del partido (partidoId), obtenida de
        // tu MatchService/MatchBean (el mismo que usas en Calendario/Standings),
        // en formato UTC ISO 8601 con "Z". Ejemplo de cómo debería quedar:
        //
        //   Match partido = matchService.obtenerPorId(partidoId);
        //   nueva.setFechaInicioPartido(partido.getFechaInicioUtc());
        //
        // Mientras no tengas esa integración, este valor fijo hará que la API
        // rechace la apuesta para cualquier partido cuya fecha real ya haya pasado.
        nueva.setFechaInicioPartido("2026-08-01T20:00:00Z");

        Prediction resultado;
        if (usarMock) {
            resultado = predictionServiceMock.crearApuesta(nueva);
        } else {
            resultado = predictionService.crearApuesta(nueva);
        }

        if (resultado != null) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Éxito",
                            "Predicción creada correctamente."));
            cargarApuestas();

            // Limpia el formulario para la siguiente predicción
            partidoId = null;
            pronostico = null;
            monto = null;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "No se pudo crear la predicción. Revisa la consola del servidor para ver el detalle (código HTTP y respuesta de la API)."));
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