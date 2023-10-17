package com.system.backend.services.impl;

import com.system.backend.entities.Usuario;
import com.system.backend.repositories.UsuarioRepository;
import com.system.backend.services.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Usuario")
public class UsuarioCRUDServiceImpl implements CRUDService {


    @Autowired
    private UsuarioRepository repository;

    @Override
    public Object create(Object[] obj) {
        Usuario aux = new Usuario();
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setEmail(obj[2] != null ? obj[2].toString() : null);
        aux.setSenha(obj[3] != null ? obj[3].toString() : null);
        aux.setTipo(obj[4] != null ? Usuario.Tipo.valueOf(obj[4].toString()) : null);
        return repository.save(aux);
    }

    @Override
    public void update(Object[] obj) {
        Usuario aux = new Usuario();
        aux.setId(Long.valueOf(obj[0].toString()));
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setEmail(obj[2] != null ? obj[2].toString() : null);
        aux.setSenha(obj[3] != null ? obj[3].toString() : null);
        aux.setTipo(obj[4] != null ? Usuario.Tipo.valueOf(obj[4].toString()) : null);
        if(repository.existsById(aux.getId())) {
            repository.save(aux);
        }
    }

    @Override
    public List<Usuario> filter(Object[] obj) {
        Usuario aux = new Usuario();
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setEmail(obj[2] != null ? obj[2].toString() : null);
        aux.setTipo(obj[4] != null ? Usuario.Tipo.valueOf(obj[4].toString()) : null);
        return repository.findByCriteria(aux);
    }

    @Override
    public List<Usuario> filterAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Object[] obj) {
        Usuario aux = new Usuario();
        aux.setId(obj[0] != null ? Long.valueOf(obj[0].toString()) : null);
        aux.setNome(obj[1] != null ? obj[1].toString() : null);
        aux.setEmail(obj[2] != null ? obj[2].toString() : null);
        aux.setSenha(obj[3] != null ? obj[3].toString() : null);
        aux.setTipo(obj[4] != null ? Usuario.Tipo.valueOf(obj[4].toString()) : null);
        repository.delete(aux);
    }
}
