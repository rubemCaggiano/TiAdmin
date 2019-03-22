package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Setor;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class SetorMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Setor> setorDao = new GenericDAO<>(Setor.class);

    private List<Setor> setores = new ArrayList<>();

    private Setor setor = new Setor();

    private Setor setorSelecionado = new Setor();

    private int id = 0;
    private boolean altera;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
//        if (has(id)) {
        if (id != 0) {
            setor = setorDao.buscarPorId(id);
            novo = false;
        } else {
            setor = new Setor();
            altera = true;
            novo = true;
        }
    }

    public void salvar() {

        try {
            setorDao.salvar(setor);
            Messages.addGlobalInfo("Setor " + setor.getSetor() + " Cadastrado com sucesso");
            setor = new Setor();
            selectAll();

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao cadastrar");
        }

    }

    public void excluir() {
        try {
            setorDao.excluir(setorSelecionado);
            setores.remove(setorSelecionado);
            Messages.addGlobalInfo("Cargo " + setorSelecionado.getSetor() + " Excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        setores = setorDao.buscarTodos(Setor.class);
    }

    public void selecionar(Setor s) {
        setorSelecionado = s;
    }

    public void deselecionar() {
        setorSelecionado = new Setor();
    }

    public void prepAlterar() {
        altera = true;
    }

    public void cancelaAlterar() {
        altera = false;
    }

// Getters and Setters   
//-------------------------------------------------------------------------------    
    public Dao<Setor> getSetorDao() {
        return setorDao;
    }

    public void setSetorDao(Dao<Setor> setorDao) {
        this.setorDao = setorDao;
    }

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Setor getSetorSelecionado() {
        return setorSelecionado;
    }

    public void setSetorSelecionado(Setor setorSelecionado) {
        this.setorSelecionado = setorSelecionado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAltera() {
        return altera;
    }

    public void setAltera(boolean altera) {
        this.altera = altera;
    }

    public boolean isNovo() {
        return novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

}
