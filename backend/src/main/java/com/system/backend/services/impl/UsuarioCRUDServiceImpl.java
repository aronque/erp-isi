package com.system.backend.services.impl;

import com.system.backend.entities.EntidadeBase;
import com.system.backend.entities.Usuario;
import com.system.backend.repositories.UsuarioRepository;
import com.system.backend.services.CRUDService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("Usuario")
public class UsuarioCRUDServiceImpl implements CRUDService {


    @Autowired
    private UsuarioRepository repository;

    @Override
    public EntidadeBase create(EntidadeBase obj) {
        return repository.save((Usuario) obj);
    }

    @Override
    public void update(EntidadeBase obj) {
        if(repository.existsById(obj.getId())) {
            repository.save((Usuario) obj);
        }
    }

    @Override
    public List<EntidadeBase> filter(EntidadeBase obj) {
        return repository.findBy((Usuario) obj).stream().map(filtered -> (EntidadeBase) filtered).collect(Collectors.toList());
    }

    @Override
    public List<EntidadeBase> filterAll() {
        return repository.findAll().stream().map(filtered -> (EntidadeBase) filtered).collect(Collectors.toList());
    }

    @Override
    public void delete(EntidadeBase obj) {
        repository.delete((Usuario) obj);
    }

    @Override
    public List<EntidadeBase> filterAll(EntidadeBase obj) {
        return null;
    }

}
