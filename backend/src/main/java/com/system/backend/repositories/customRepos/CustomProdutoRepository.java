package com.system.backend.repositories.customRepos;

import com.system.backend.entities.Produto;

import java.util.List;

public interface CustomProdutoRepository {

    public List<Produto> findBy(Produto produto);
}
