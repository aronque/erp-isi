package com.system.backend.services;

import org.springframework.stereotype.Service;

/**
 * Classe de serviços relacionados ao controle de acesso
 * @author aronque
 */
@Service
public interface ControleAcessoService {


    /**
     * Faz a verificação se o usuário tem permissão para utilizar a funcionalidade com base no tipo do seu usuário
     * @author aronque
     * @param idUsuario ID do usuário
     * @param funcionalidade String constante com o nome-tipo da funcionalidade
     * @return boolean true caso o usuário tenha permissão, false caso não
     */
    public boolean temPersmissao(Long idUsuario, String funcionalidade);
}
