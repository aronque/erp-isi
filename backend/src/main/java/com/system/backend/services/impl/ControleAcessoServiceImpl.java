package com.system.backend.services.impl;

import com.system.backend.entities.Usuario;
import com.system.backend.repositories.UsuarioRepository;
import com.system.backend.services.ControleAcessoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ControleAcessoServiceImpl implements ControleAcessoService {


    @Autowired
    private UsuarioRepository repository;

    private static Map<String, List<Integer>> funcMap = new HashMap<>();

    /**
     * 1 - Usuario do estoque
     * 2 - Usuario do estoque (gerente)
     * 3 - Usuario comum (realiza pedido de saida do estoque)
     * 4 - Usuario adm/suporte)
     */
    static {
        funcMap.put("CRUD", Arrays.asList(1, 2, 4));
        funcMap.put("RELATORIO", Arrays.asList(1, 2, 4));
        funcMap.put("CRUDPEDIDO", Arrays.asList(1, 2, 3, 4));
        funcMap.put("CRUDUSUARIO", Arrays.asList(2, 4));
    }


    @Override
    public boolean temPersmissao(Long idUsuario, String funcionalidade) {
        return funcMap.get(funcionalidade).contains((repository.hasPermission(idUsuario)).intValue());
    }
}
