package com.utn.golmundial.publicfrontend.bean;

import com.utn.golmundial.publicfrontend.model.Wallet;
import com.utn.golmundial.publicfrontend.services.WalletService;
import com.utn.golmundial.publicfrontend.services.WalletServiceMock;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class WalletBean implements Serializable {

    // Mientras Jonathan no tenga la API prendida, deja usarMock = true.
    // Cuando la API esté corriendo de verdad, cambia esto a false.
    private static final boolean usarMock = true;

    private final WalletService walletService = new WalletService();
    private final WalletServiceMock walletServiceMock = new WalletServiceMock();

    private Wallet wallet;

    // TODO: reemplaza esto por el id real del usuario logueado
    // (por ejemplo, tomándolo de tu LoginBean o de la sesión)
    private Long usuarioId = 995L;

    @PostConstruct
    public void init() {
        if (usarMock) {
            wallet = walletServiceMock.obtenerBilletera(usuarioId);
        } else {
            wallet = walletService.obtenerBilletera(usuarioId);
        }
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }
}