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
    private boolean altera;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
//        if (has(id)) {
        if (id != 0) {
            sistema = soDao.buscarPorId(id);
            novo = false;
        } else {
            sistema = new SistemaOperacional();
            altera = true;
            novo = true;
        }
    }

    public void salvar() {

        try {
            soDao.salvar(sistema);
            Messages.addGlobalInfo("Sistema Operacional " + sistema.getVersao() + " Cadastrado com sucesso");
            sistema = new SistemaOperacional();
            selectAll();

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao cadastrar");
        }

    }

    public void excluir() {
        try {
            soDao.excluir(sistemaSelecionado);
            sistemas.remove(sistemaSelecionado);
            Messages.addGlobalInfo("Sistema Operacional " + sistema.getVersao() + " Excluido com sucesso");

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

    public void prepAlterar() {
        altera = true;
    }

    public void cancelaAlterar() {
        altera = false;
    }

//    Getters and Setters
//------------------------------------------------------------------------------    
    public Dao<SistemaOperacional> getSoDao() {
        return soDao;
    }

    public void setSoDao(Dao<SistemaOperacional> soDao) {
        this.soDao = soDao;
    }

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
