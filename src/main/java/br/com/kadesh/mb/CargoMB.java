package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Cargo;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class CargoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Cargo> cargoDao = new GenericDAO<>(Cargo.class);

    private List<Cargo> cargos = new ArrayList<>();

    private Cargo cargo = new Cargo();
    private Cargo cargoSelecionado = new Cargo();

    public void salvar() {

        try {
            cargoDao.salvar(cargo);
            cargo = new Cargo();
            selectAll();
            Messages.addGlobalInfo("Cargo " + cargoSelecionado.getCargo() + " Cadastrado com sucesso");
        } catch (Exception e) {
            Messages.addGlobalError("Falha ao cadastrar");
        }

    }
    

    public void excluir() throws IOException {
        try {
            cargoDao.excluir(cargoSelecionado);
            cargos.remove(cargoSelecionado);
            Messages.addGlobalInfo("Cargo " + cargoSelecionado.getCargo() + " Excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        cargos = cargoDao.buscarTodos(Cargo.class);
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

}
