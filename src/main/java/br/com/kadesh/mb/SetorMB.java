package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Setor;
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
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
//        if (has(id)) {
        if (id != 0) {
            setor = setorDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            setor = new Setor();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                setorDao.salvar(setor);
                Messages.addGlobalInfo("Cadastrado realizado com sucesso!");
                setor = new Setor();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/setores.jsf");

            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }
        } else {
            try {
                setorDao.alterar(setor);
                Messages.addGlobalInfo("Cadastro alterado com sucesso!");
                setor = new Setor();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/setores.jsf");

            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }

    }

    public void excluir() {
        try {
            setorDao.excluir(setorSelecionado);
            setores.remove(setorSelecionado);
            Messages.addGlobalInfo("Cadastro excluido com sucesso");

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

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEdicao() {
        editavel = false;
    }

// Getters and Setters   
//-------------------------------------------------------------------------------    
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
