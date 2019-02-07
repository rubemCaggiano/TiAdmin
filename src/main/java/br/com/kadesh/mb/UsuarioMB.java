package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Cargo;
import br.com.kadesh.model.Usuario;
import static com.github.adminfaces.starter.util.Utils.addDetailMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Usuario> usuarioDao = new GenericDAO<>(Usuario.class);
    private Dao<Cargo> cargoDao = new GenericDAO<>(Cargo.class);

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Cargo> cargos = new ArrayList<>();

    private Usuario usuario = new Usuario();
    private Cargo cargo = new Cargo();

    public void salvar() {
        String msg;

        usuarioDao.salvar(usuario);
        msg = "Usuário " + usuario.getNome() + " Cadastrado com sucesso";
        usuario = new Usuario();
        addDetailMessage(msg);
    }

    @PostConstruct
    public void selectAll() {
        usuarios = usuarioDao.buscarTodos(Usuario.class);
        cargos = cargoDao.buscarTodos(Cargo.class);
    }

//    Getters and Setters
//    ---------------------------------------------------------------------------
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Dao<Usuario> getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(Dao<Usuario> usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Dao<Cargo> getCargoDao() {
        return cargoDao;
    }

    public void setCargoDao(Dao<Cargo> cargoDao) {
        this.cargoDao = cargoDao;
    }

}
