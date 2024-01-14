package com.system.backend.controllers;

import com.system.backend.entities.MailInfos;
import com.system.backend.entities.Usuario;
import com.system.backend.services.CRUDService;
import com.system.backend.services.ControleAcessoService;
import com.system.backend.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe controladora de requests relacionados a entidade usuários
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final String FUNC_CONST = "CRUDUSUARIO";


    @Autowired
    @Qualifier("Usuario")
    CRUDService crudService;

    @Autowired
    EmailService emailService;

    @Autowired
    ControleAcessoService accControlService;

    @PostMapping("/insert")
    public ResponseEntity createUsuario(@RequestBody Usuario usuario) {
        try {
            if(!accControlService.temPersmissao(usuario.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }

            Usuario criado = (Usuario) crudService.create(usuario);

            MailInfos infos = new MailInfos();
            infos.setEmailTo(criado.getEmail());
            infos.setMessage("Um usuário foi criado com sucesso para este email! Segue os dados para utilização no sistema: \nUser: " + criado.getEmail() + "\nSenha:" + criado.getSenha() + "\n\nCaso não tenha sido você que criou este usuário, entre em contato respondendo este email.");
            infos.setSubject("Confirmação - Criação de Usuário");
            emailService.sendEmail(infos);

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(crudService.filterAll().stream().map(filtered -> (Usuario) filtered).collect(Collectors.toList()));
    }

    @PostMapping("/findByCriteria")
    public ResponseEntity<List<Usuario>> findByCriteria(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(crudService.filter(usuario).stream().map(filtered -> (Usuario) filtered).collect(Collectors.toList()));
    }

    @PutMapping("/update")
    public ResponseEntity updateUsuario(@RequestBody Usuario usuario) {
        try {
            if(!accControlService.temPersmissao(usuario.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            Usuario usuarioAux = new Usuario();
            usuarioAux.setId(usuario.getId());
            usuarioAux = (Usuario) ((ArrayList<?>) crudService.filter(usuarioAux)).get(0);

            setUpdatedFields(usuario, usuarioAux);
            crudService.update(usuarioAux);

            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody Usuario usuario) {
        try {
            if(!accControlService.temPersmissao(usuario.getRequestUser().getId(), FUNC_CONST)) {
                return ResponseEntity.ok("405");
            }
            crudService.delete(usuario);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            return ResponseEntity.ok("500");
        }
    }

    private void setUpdatedFields(Usuario usuarioParam, Usuario usuarioAux) {
        usuarioAux.setNome(usuarioParam.getNome() != null ? usuarioParam.getNome() : usuarioAux.getNome());
        usuarioAux.setEmail(usuarioParam.getEmail() != null ? usuarioParam.getEmail() : usuarioAux.getEmail());
        usuarioAux.setSenha(usuarioParam.getSenha() != null ? usuarioParam.getSenha() : usuarioAux.getSenha());
        usuarioAux.setTipo(usuarioParam.getTipo() != null ? usuarioParam.getTipo() : usuarioAux.getTipo());
    }
}
