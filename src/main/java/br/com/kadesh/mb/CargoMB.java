package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Cargo;
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
public class CargoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Cargo> cargoDao = new GenericDAO<>(Cargo.class);

    private List<Cargo> cargos = new ArrayList<>();

    private Cargo cargo = new Cargo();
    private Cargo cargoSelecionado = new Cargo();

    private int id = 0;
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            cargo = cargoDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            cargo = new Cargo();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                cargoDao.salvar(cargo);
                Messages.addGlobalInfo("Cadastrado realizado com sucesso!");
                cargo = new Cargo();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/cargos.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }

        } else {
            try {
                cargoDao.alterar(cargo);
                Messages.addGlobalInfo("Cadastro alterado com sucesso!");
                cargo = new Cargo();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/cargos.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }
    }

    public void excluir() {
        try {
            cargoDao.excluir(cargoSelecionado);
            cargos.remove(cargoSelecionado);
            Messages.addGlobalInfo("Cadastro excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        cargos = cargoDao.buscarTodos(Cargo.class);
    }

    public void selecionar(Cargo c) {
        cargoSelecionado = c;
    }

    public void deselecionar() {
        cargoSelecionado = new Cargo();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEditavel() {
        editavel = false;
    }

//    Getters and Setters
//    ---------------------------------------------------------------------------
    public Dao<Cargo> getCargoDao() {
        return cargoDao;
    }

    public void setCargoDao(Dao<Cargo> cargoDao) {
        this.cargoDao = cargoDao;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Cargo getCargoSelecionado() {
        return cargoSelecionado;
    }

    public void setCargoSelecionado(Cargo cargoSelecionado) {
        this.cargoSelecionado = cargoSelecionado;
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
