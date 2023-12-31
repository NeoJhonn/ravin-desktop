package controllers;

import builders.Builder;
import enums.CategoriaCardapio;
import enums.TipoProduto;
import models.Produto;
import repositories.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private ProdutoRepository repository;

    public ProdutoController() {

        repository = new ProdutoRepository();
        Builder.seedProdutos(repository.listarTodos());
    }

    public void cadastrar(Produto entidade) {
        repository.salvar(entidade);
    }

    public void alterar(Produto entidade) {
        // REGRAS DE NEGÓCIO
        repository.salvar(entidade);
    }

    public void excluir(int id) {
        Produto produtoBuscado = repository.buscarPorId(id);

        if (produtoBuscado != null) {
            repository.excluir(produtoBuscado);
        }
    }

    public Produto consultar(int id) {

        return repository.buscarPorId(id);
    }

    public List<Produto> listarTodos() {

        return repository.listarTodos();
    }

    public String listarProdutosCardapio(CategoriaCardapio categoriaCardapio) {
        String produtos = "";

        for (Produto p: repository.listarTodos()) {
            if ( String.valueOf(p.getTipoProduto()) == String.valueOf(categoriaCardapio)) {
                produtos += p.getNome() +", ";
            }
        }

        return produtos;
    }
}
