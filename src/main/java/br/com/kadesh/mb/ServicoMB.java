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
    private boolean altera;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            servico = servicoDao.buscarPorId(id);
            novo = false;
        } else {
            servico = new Servico();
            altera = true;
            novo = true;
        }
    }

    public void salvar() {

        try {
            servicoDao.salvar(servico);
            Messages.addGlobalInfo("Serviço " + servico.getServico() + " Cadastrado com sucesso");
            servico = new Servico();
            selectAll();

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao cadastrar");
        }

    }

    public void excluir() {
        try {
            servicoDao.excluir(servicoSelecionado);
            servicos.remove(servicoSelecionado);
            Messages.addGlobalInfo("Serviço " + servico.getServico() + " Excluido com sucesso");

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

    public void prepAlterar() {
        altera = true;
    }

    public void cancelaAlterar() {
        altera = false;
    }

//Getters and Setters
//------------------------------------------------------------------------------    
    public Dao<Servico> getServicoDao() {
        return servicoDao;
    }

    public void setServicoDao(Dao<Servico> servicoDao) {
        this.servicoDao = servicoDao;
    }

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
