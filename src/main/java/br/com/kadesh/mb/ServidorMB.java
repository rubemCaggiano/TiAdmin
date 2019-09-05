package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Programa;
import br.com.kadesh.model.Servico;
import br.com.kadesh.model.Servidor;
import br.com.kadesh.model.SistemaOperacional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class ServidorMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Servidor> servidorDao = new GenericDAO<>(Servidor.class);
    private Dao<SistemaOperacional> soDao = new GenericDAO<>(SistemaOperacional.class);
    private Dao<Programa> programaDao = new GenericDAO<>(Programa.class);
    private Dao<Servico> servicoDao = new GenericDAO<>(ServicoMB.class);

    private List<Servidor> servidores = new ArrayList<>();
    private List<Servidor> hypervisores = new ArrayList<>();
    private List<SistemaOperacional> sistemas = new ArrayList<>();
    private List<Programa> programas = new ArrayList<>();
    private List<Servico> servicos = new ArrayList<>();

    private Servidor servidor = new Servidor();
    private Servidor host = new Servidor();
    private Servidor servidorSelecionado = new Servidor();
    private Programa programa = new Programa();
    private SistemaOperacional sistema = new SistemaOperacional();
    private Servico servico = new Servico();

    private int id = 0;
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            servidor = servidorDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            servidor = new Servidor();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                servidor.setDataCadastro(new Date());
                host = servidor.getHost();
                host.getVms().add(servidor);
                servidorDao.salvar(servidor);
                servidorDao.alterar(host);
                Messages.addGlobalInfo("Cadastrado realizado com sucesso!");
                servidor = new Servidor();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/servidores.jsf");

            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }
        } else {
            try {
                servidor.setDataAlteracao(new Date());
                servidorDao.alterar(servidor);
                Messages.addGlobalInfo("Cadastro alterado com sucesso!");
                servidor = new Servidor();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/servidores.jsf");

            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }

    }

    public void excluir() {
        try {
            servidorDao.excluir(servidorSelecionado);
            servidores.remove(servidorSelecionado);
            Messages.addGlobalInfo("Cadastro excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    public void adicionarServico() {
        servidorSelecionado.getServicos().add(servico);
        servicos.remove(servico);
        servico = new Servico();
    }

    public void salvarServicos() {
        servidorDao.alterar(servidorSelecionado);
    }

    @PostConstruct
    public void selectAll() {
        servidores = servidorDao.buscarTodos(Servidor.class);
        programas = programaDao.buscarTodos(Programa.class);
        sistemas = soDao.buscarTodos(SistemaOperacional.class);
        servicos = servicoDao.buscarTodos(Servico.class);
//        hypervisores = servidorDao.buscarTodos(Servidor.class);
        for (Servidor s : servidores) {
            if (s.isHypervisor()) {
                hypervisores.add(s);
            }
        }
    }

    public void selecionar(Servidor c) {
        servidorSelecionado = c;
    }

    public void deselecionar() {
        servidorSelecionado = new Servidor();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEdicao() {
        editavel = false;
    }

//    Getters and Setter
//           -------------------------------------------------------------------
    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public Servico getServico() {
        return servico;
    }

    public List<Servidor> getHypervisores() {
        return hypervisores;
    }

    public void setHypervisores(List<Servidor> hypervisores) {
        this.hypervisores = hypervisores;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public List<Servidor> getServidores() {
        return servidores;
    }

    public void setServidores(List<Servidor> servidores) {
        this.servidores = servidores;
    }

    public List<SistemaOperacional> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<SistemaOperacional> sistemas) {
        this.sistemas = sistemas;
    }

    public List<Programa> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Programa> programas) {
        this.programas = programas;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Servidor getServidorSelecionado() {
        return servidorSelecionado;
    }

    public void setServidorSelecionado(Servidor servidorSelecionado) {
        this.servidorSelecionado = servidorSelecionado;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public SistemaOperacional getSistema() {
        return sistema;
    }

    public void setSistema(SistemaOperacional sistema) {
        this.sistema = sistema;
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
