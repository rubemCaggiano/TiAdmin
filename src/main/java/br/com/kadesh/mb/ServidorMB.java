package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Programa;
import br.com.kadesh.model.Servidor;
import br.com.kadesh.model.SistemaOperacional;
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
public class ServidorMB {

    private static final long serialVersionUID = 1L;

    private Dao<Servidor> servidorDao = new GenericDAO<>(Servidor.class);
    private Dao<SistemaOperacional> soDao = new GenericDAO<>(SistemaOperacional.class);
    private Dao<Programa> programaDao = new GenericDAO<>(Programa.class);

    private List<Servidor> servidores = new ArrayList<>();
    private List<SistemaOperacional> sistemas = new ArrayList<>();
    private List<Programa> programas = new ArrayList<>();

    private Servidor servidor = new Servidor();
    private Servidor servidorSelecionado = new Servidor();
    private Programa programa = new Programa();
    private SistemaOperacional sistema = new SistemaOperacional();

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
                servidorDao.salvar(servidor);
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

    @PostConstruct
    public void selectAll() {
        servidores = servidorDao.buscarTodos(Servidor.class);
        programas = programaDao.buscarTodos(Programa.class);
        sistemas = soDao.buscarTodos(SistemaOperacional.class);
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
//            -------------------------------------------------------------------
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
