package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Backup;
import br.com.kadesh.model.Servidor;
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
public class BackupMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Backup> backupDao = new GenericDAO<>(Backup.class);
    private Dao<Servidor> servidorDao = new GenericDAO<>(Servidor.class);

    private List<Servidor> servidores = new ArrayList<>();
    private List<Backup> backups = new ArrayList<>();
    private List<String> exclusoes = new ArrayList<>();

    private Backup backup = new Backup();
    private Backup backupSelecionado = new Backup();
    private Servidor servidor = new Servidor();
    private String exclusao;

    private int id = 0;
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            backup = backupDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            backup = new Backup();
            editavel = true;
            novo = true;
        }
    }

    public void adicionarExclusao() {
        exclusoes.add(exclusao);
    }

    public void salvar() {
        if (novo) {
            try {
                backupDao.salvar(backup);
                Messages.addGlobalInfo("Cadastrado realizado com sucesso!");
                backup = new Backup();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/backups.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }

        } else {
            try {
                backupDao.alterar(backup);
                Messages.addGlobalInfo("Cadastro alterado com sucesso!");
                backup = new Backup();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/backups.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }
    }

    public void excluir() {
        try {
            backupDao.excluir(backupSelecionado);
            backups.remove(backupSelecionado);
            Messages.addGlobalInfo("Cadastro excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        backups = backupDao.buscarTodos(Backup.class);
        servidores = servidorDao.buscarTodos(Servidor.class);
    }

    public void selecionar(Backup b) {
        backupSelecionado = b;
    }

    public void deselecionar() {
        backupSelecionado = new Backup();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEditavel() {
        editavel = false;
    }

//    --------------------------------------------------------------------------
    public List<Backup> getBackups() {
        return backups;
    }

    public void setBackups(List<Backup> backups) {
        this.backups = backups;
    }

    public Backup getBackup() {
        return backup;
    }

    public void setBackup(Backup backup) {
        this.backup = backup;
    }

    public Backup getBackupSelecionado() {
        return backupSelecionado;
    }

    public void setBackupSelecionado(Backup backupSelecionado) {
        this.backupSelecionado = backupSelecionado;
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

    public List<Servidor> getServidores() {
        return servidores;
    }

    public void setServidores(List<Servidor> servidores) {
        this.servidores = servidores;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public String getExclusao() {
        return exclusao;
    }

    public void setExclusao(String exclusao) {
        this.exclusao = exclusao;
    }

    public List<String> getExclusoes() {
        return exclusoes;
    }

    public void setExclusoes(List<String> exclusoes) {
        this.exclusoes = exclusoes;
    }

}
