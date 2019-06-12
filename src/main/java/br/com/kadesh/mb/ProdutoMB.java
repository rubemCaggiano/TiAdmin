package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class ProdutoMB {

    private static final long serialVersionUID = 1L;

    private Dao<Produto> produtoDao = new GenericDAO<>(Produto.class);

    private List<Produto> produtos = new ArrayList<>();

    private Produto produto = new Produto();
    private Produto produtoSelecionado = new Produto();

    private int id = 0;
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            produto = produtoDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            produto = new Produto();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                produtoDao.salvar(produto);
                Messages.addGlobalInfo("Produto " + produto.getProduto() + " Cadastrado com sucesso");
                produto = new Produto();
                selectAll();
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }

        } else {
            try {
                produtoDao.alterar(produto);
                Messages.addGlobalInfo("Produto " + produto.getProduto() + " Alterado com sucesso");
                produto = new Produto();
                selectAll();
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }
    }

    public void excluir() {
        try {
            produtoDao.excluir(produtoSelecionado);
            produtos.remove(produtoSelecionado);
            Messages.addGlobalInfo("Produto " + produtoSelecionado.getProduto() + " Excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        produtos = produtoDao.buscarTodos(Produto.class);
    }

    public void selecionar(Produto c) {
        produtoSelecionado = c;
    }

    public void deselecionar() {
        produtoSelecionado = new Produto();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEditavel() {
        editavel = false;
    }

//    Getters and Setters
//    ----------------------------------------------------------------------------
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(Produto produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEditavel() {
        return editavel;
    }

    public void setEditavel(boolean editavel) {
        this.editavel = editavel;
    }

    public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

}
