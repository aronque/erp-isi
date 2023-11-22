package com.system.backend.services;

import com.system.backend.entities.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface CRUDService {

    public Object create(Object[] obj);

    public void delete(Object[] obj);

    public void update(Object[] obj);

    public Object filter(Object[] obj);

    public Object filterAll();

    public Object filterAll(Object[] obj);

    default Usuario login(String user, String senha) {
        return null;
    }
}
