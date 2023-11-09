package com.system.backend.controllers;

import com.system.backend.entities.Usuario;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    @Qualifier("Usuario")
    CRUDService crudService;

    @PostMapping("/insert")
    public ResponseEntity createUsuario(@RequestBody Usuario usuario) {
        Object[] objAux = new Object[5];
        objAux[1] = usuario.getNome();
        objAux[2] = usuario.getEmail();
        objAux[3] = usuario.getSenha();
        objAux[4] = usuario.getTipo();
        crudService.create(objAux);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok((List<Usuario>) crudService.filterAll());
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<Usuario>> findByCriteria(@RequestBody Usuario usuario) {
        Object[] objAux = new Object[5];
        objAux[0] = usuario.getId();
        objAux[1] = usuario.getNome();
        objAux[2] = usuario.getEmail();
        objAux[4] = usuario.getTipo();
        return ResponseEntity.ok((List<Usuario>) crudService.filter(objAux));
    }

    @PutMapping("/update")
    public ResponseEntity updateUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioAux;
        Object[] objAux = new Object[5];
        objAux[0] = usuario.getId();

        usuarioAux = (Usuario) ((ArrayList<?>) crudService.filter(objAux)).get(0);

        setUpdatedFields(objAux, usuario, usuarioAux);
        crudService.update(objAux);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Usuario usuario) {
        Object[] objAux = new Object[5];
        objAux[0] = usuario.getId();
        objAux[1] = usuario.getNome();
        objAux[2] = usuario.getEmail();
        objAux[3] = usuario.getSenha();
        objAux[4] = usuario.getTipo();
        crudService.delete(objAux);
        return ResponseEntity.ok().build();
    }

    private void setUpdatedFields(Object[] objAux, Usuario usuarioParam, Usuario usuarioAux) {
        objAux[1] = usuarioParam.getNome() != null ? usuarioParam.getNome() : usuarioAux.getNome();
        objAux[2] = usuarioParam.getEmail() != null ? usuarioParam.getEmail() : usuarioAux.getEmail();
        objAux[3] = usuarioParam.getSenha() != null ? usuarioParam.getSenha() : usuarioAux.getSenha();
        objAux[4] = usuarioParam.getTipo() != null ? usuarioParam.getTipo() : usuarioAux.getTipo();
    }
}
