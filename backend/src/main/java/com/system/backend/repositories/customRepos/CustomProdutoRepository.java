package com.system.backend.repositories.customRepos;

import com.system.backend.entities.EntidadeBase;
import com.system.backend.entities.Produto;

import java.util.List;

/**
 * Repository customizado para operações em banco relacionadas a entidade Produto
 * @author aronque
 */
public interface CustomProdutoRepository {


    /**
     * Filtro customizado com base nos dados fornecidos
     * @author aronque
     * @param produto Objeto produto com dados fornecidos para filtro customizado
     * @return List<Produto> Lista contendo os produtos encontrados com base nos dados fornecidos
     */
    public List<EntidadeBase> findBy(Produto produto);
}
