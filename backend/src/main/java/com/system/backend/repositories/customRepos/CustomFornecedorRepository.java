package com.system.backend.repositories.customRepos;

import com.system.backend.entities.Fornecedor;

import java.util.List;

/**
 * Repository customizado para operações em banco relacionadas a entidade Fornecedor
 * @author aronque
 */
public interface CustomFornecedorRepository {


    /**
     * Filtro customizado com base nos dados fornecidos
     * @author aronque
     * @param fornecedor Objeto fornecedor com dados fornecidos para filtro customizado
     * @return List<Fornecedor> Lista contendo os fornecedores encontrados com base nos dados fornecidos
     */
    public List<Fornecedor> findBy(Fornecedor fornecedor);
}
