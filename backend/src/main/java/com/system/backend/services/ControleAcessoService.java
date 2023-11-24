package com.system.backend.services;

import org.springframework.stereotype.Service;

@Service
public interface ControleAcessoService {

    public boolean temPersmissao(Long idUsuario, String funcionalidade);
}
