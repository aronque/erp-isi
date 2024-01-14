package com.system.backend.services;

import com.system.backend.entities.EntidadeBase;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe genérica de serviços relacionados as entidades principais do sistema
 * @author aronque
 */
@Service
public interface CRUDService {


    /**
     * Cria uma nova tupla com as informações do objeto fornecido
     * @author aronque
     * @param entity Entidade genérica contendo os dados da entidade
     * @return EntidadeBase retorna as informações da criação da entidade
     */
    public EntidadeBase create(EntidadeBase entity);


    /**
     * Delete uma tupla com base nas informações do objeto fornecido
     * @author aronque
     * @param entity Array de Objetos genéricos contendo os dados da entidade
     */
    public void delete(EntidadeBase entity);


    /**
     * Modifica uma tupla com base nas informações do objeto fornecido
     * @author aronque
     * @param entity Array de Objetos genéricos contendo os dados da entidade
     */
    public void update(EntidadeBase entity);


    /**
     * Filtra uma tupla com base nas informações do objeto fornecido
     * @author aronque
     * @param entity Array de Objetos genéricos contendo os dados da entidade
     * @return EntidadeBase retorna as informações do objeto filtrado
     */
    public List<EntidadeBase> filter(EntidadeBase entity);


    /**
     * Filtra todas as entidades do tipo fornecido
     * @author aronque
     * @return EntidadeBase retorna os dados dos objetos encontrados
     */
    public List<EntidadeBase> filterAll();


    /**
     * Filtra todas as entidades do tipo fornecido
     * @author aronque
     * @return EntidadeBase retorna os dados dos objetos encontrados
     */
    public List<EntidadeBase> filterAll(EntidadeBase entity);

}
