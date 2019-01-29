package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Estacao;
import br.com.kadesh.model.Maquina;
import br.com.kadesh.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class EstacaoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Estacao> estacaoDao = new GenericDAO<>(Estacao.class);
    private Dao<Usuario> usuarioDao = new GenericDAO<>(Usuario.class);

    private List<Maquina> maquinas = new ArrayList<>();
    private List<Estacao> estacoes = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    private Estacao estacao = new Estacao();
    private Estacao estacaoSelecionada = new Estacao();
    private Usuario usuario = new Usuario();

    @PostConstruct
    public void selectAll() {
        estacoes = estacaoDao.buscarTodos(Estacao.class);
        usuarios = usuarioDao.buscarTodos(Usuario.class);
    }

//    Getters and Setters 
//    ----------------------------------------------------------------------------        
    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<Maquina> maquinas) {
        this.maquinas = maquinas;
    }

    public List<Estacao> getEstacoes() {
        return estacoes;
    }

    public void setEstacoes(List<Estacao> estacoes) {
        this.estacoes = estacoes;
    }

    public Estacao getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacao estacao) {
        this.estacao = estacao;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estacao getEstacaoSelecionada() {
        return estacaoSelecionada;
    }

    public void setEstacaoSelecionada(Estacao estacaoSelecionada) {
        this.estacaoSelecionada = estacaoSelecionada;
    }

}
