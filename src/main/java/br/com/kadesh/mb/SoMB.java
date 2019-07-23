package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.SistemaOperacional;
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
public class SoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<SistemaOperacional> soDao = new GenericDAO<>(SistemaOperacional.class);

    private List<SistemaOperacional> sistemas = new ArrayList<>();

    private SistemaOperacional sistema = new SistemaOperacional();

    private SistemaOperacional sistemaSelecionado = new SistemaOperacional();

    private int id = 0;
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            sistema = soDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            sistema = new SistemaOperacional();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                soDao.salvar(sistema);
                Messages.addGlobalInfo("Cadastrado realizado com sucesso!");
                sistema = new SistemaOperacional();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/sistemas.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }
        } else {
            try {
                soDao.alterar(sistema);
                Messages.addGlobalInfo("Cadastro alterado com sucesso!");
                sistema = new SistemaOperacional();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/sistemas.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }
    }

    public void excluir() {
        try {
            soDao.excluir(sistemaSelecionado);
            sistemas.remove(sistemaSelecionado);
            Messages.addGlobalInfo("Cadastro excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        sistemas = soDao.buscarTodos(SistemaOperacional.class);
    }

    public void selecionar(SistemaOperacional s) {
        sistemaSelecionado = s;
    }

    public void deselecionar() {
        sistemaSelecionado = new SistemaOperacional();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEdicao() {
        editavel = false;
    }

//    Getters and Setters
//------------------------------------------------------------------------------    
    public List<SistemaOperacional> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<SistemaOperacional> sistemas) {
        this.sistemas = sistemas;
    }

    public SistemaOperacional getSistema() {
        return sistema;
    }

    public void setSistema(SistemaOperacional sistema) {
        this.sistema = sistema;
    }

    public SistemaOperacional getSistemaSelecionado() {
        return sistemaSelecionado;
    }

    public void setSistemaSelecionado(SistemaOperacional sistemaSelecionado) {
        this.sistemaSelecionado = sistemaSelecionado;
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
