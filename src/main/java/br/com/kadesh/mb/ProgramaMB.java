package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Programa;
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
public class ProgramaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Programa> programaDao = new GenericDAO<>(Programa.class);

    private List<Programa> programas = new ArrayList<>();

    private Programa programa = new Programa();
    private Programa programaSelecionado = new Programa();

    private int id = 0;
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
//        if (has(id)) {
        if (id != 0) {
            programa = programaDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            programa = new Programa();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                programaDao.salvar(programa);
                Messages.addGlobalInfo("Cadastrado realizado com sucesso!");
                programa = new Programa();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/programas.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }
        } else {
            try {
                programaDao.alterar(programa);
                Messages.addGlobalInfo("Cadastro alterado com sucesso!");
                programa = new Programa();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/programas.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }

    }

    public void excluir() {
        try {
            programaDao.excluir(programaSelecionado);
            programas.remove(programaSelecionado);
            Messages.addGlobalInfo("Cadastro excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        programas = programaDao.buscarTodos(Programa.class);
    }

    public void selecionar(Programa c) {
        programaSelecionado = c;
    }

    public void deselecionar() {
        programaSelecionado = new Programa();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEdicao() {
        editavel = false;
    }

//    Getters and Setters
//    ---------------------------------------------------------------------------
    public Dao<Programa> getProgramaDao() {
        return programaDao;
    }

    public void setProgramaDao(Dao<Programa> programaDao) {
        this.programaDao = programaDao;
    }

    public List<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Programa> programas) {
        this.programas = programas;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Programa getProgramaSelecionado() {
        return programaSelecionado;
    }

    public void setProgramaSelecionado(Programa programaSelecionado) {
        this.programaSelecionado = programaSelecionado;
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
