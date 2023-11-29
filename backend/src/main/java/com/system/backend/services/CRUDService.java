package com.system.backend.services;

import org.springframework.stereotype.Service;

/**
 * Classe genérica de serviços relacionados as entidades principais do sistema
 * @author aronque
 */
@Service
public interface CRUDService {


    /**
     * Cria uma nova tupla com as informações do objeto fornecido
     * @author aronque
     * @param obj Array de Objetos genéricos contendo os dados da entidade
     * @return Object retorna as informações da criação da entidade
     */
    public Object create(Object[] obj);


    /**
     * Delete uma tupla com base nas informações do objeto fornecido
     * @author aronque
     * @param obj Array de Objetos genéricos contendo os dados da entidade
     */
    public void delete(Object[] obj);


    /**
     * Modifica uma tupla com base nas informações do objeto fornecido
     * @author aronque
     * @param obj Array de Objetos genéricos contendo os dados da entidade
     */
    public void update(Object[] obj);


    /**
     * Filtra uma tupla com base nas informações do objeto fornecido
     * @author aronque
     * @param obj Array de Objetos genéricos contendo os dados da entidade
     * @return Object retorna as informações do objeto filtrado
     */
    public Object filter(Object[] obj);


    /**
     * Filtra todas as entidades do tipo fornecido
     * @author aronque
     * @return Object retorna os dados dos objetos encontrados
     */
    public Object filterAll();


    /**
     * Filtra todas as entidades do tipo fornecido
     * @author aronque
     * @return Object retorna os dados dos objetos encontrados
     */
    public Object filterAll(Object[] obj);

}
