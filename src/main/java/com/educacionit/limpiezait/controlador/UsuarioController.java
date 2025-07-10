package com.educacionit.limpiezait.controlador;

import com.educacionit.limpiezait.dominio.Usuario;
import com.educacionit.limpiezait.servicio.UserRandomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/randomuser")
public class UsuarioController {

    private UserRandomService userRandomService = new UserRandomService();

    @GetMapping
    public ResponseEntity<Usuario> randomUser() {
        return ResponseEntity.ok(this.userRandomService.getRandoUser());
    }
}
