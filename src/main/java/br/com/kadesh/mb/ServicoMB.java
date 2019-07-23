package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Servico;
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
public class ServicoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Servico> servicoDao = new GenericDAO<>(Servico.class);
    private List<Servico> servicos = new ArrayList<>();
    private Servico servico = new Servico();
    private Servico servicoSelecionado = new Servico();

    private int id = 0;
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            servico = servicoDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            servico = new Servico();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                servicoDao.salvar(servico);
                Messages.addGlobalInfo("Cadastrado realizado com sucesso!");
                servico = new Servico();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/servicos.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }
        } else {
            try {
                servicoDao.alterar(servico);
                Messages.addGlobalInfo("Cadastro alterado com sucesso!");
                servico = new Servico();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/servicos.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }

    }

    public void excluir() {
        try {
            servicoDao.excluir(servicoSelecionado);
            servicos.remove(servicoSelecionado);
            Messages.addGlobalInfo("Cadastro excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        servicos = servicoDao.buscarTodos(Servico.class);
    }

    public void selecionar(Servico s) {
        servicoSelecionado = s;
    }

    public void deselecionar() {
        servicoSelecionado = new Servico();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEdicao() {
        editavel = false;
    }

//Getters and Setters
//------------------------------------------------------------------------------    
    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }

    public void setServicoSelecionado(Servico servicoSelecionado) {
        this.servicoSelecionado = servicoSelecionado;
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
