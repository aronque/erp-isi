package com.system.backend.repositories.customRepos;

import com.system.backend.entities.Usuario;

import java.util.List;

public interface CustomUsuarioRepository {

    public List<Usuario> findBy(Usuario usuario);

    public Usuario login(String user, String senha);
}
