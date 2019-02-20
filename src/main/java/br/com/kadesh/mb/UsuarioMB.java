package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Cargo;
import br.com.kadesh.model.Usuario;
import static com.github.adminfaces.starter.util.Utils.addDetailMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Faces;
import static com.github.adminfaces.template.util.Assert.has;
import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class UsuarioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Usuario> usuarioDao = new GenericDAO<>(Usuario.class);
    private Dao<Cargo> cargoDao = new GenericDAO<>(Cargo.class);

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Cargo> cargos = new ArrayList<>();

    private Usuario usuario;

    private Usuario usuarioSelecionado = new Usuario();

    private Cargo cargo = new Cargo();

    private int id = 0;
    private boolean altera;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
//        if (has(id)) {
        if (id != 0) {
            usuario = usuarioDao.buscarPorId(id);
            altera = true;
        } else {
            usuario = new Usuario();
            altera = false;
        }
    }

    public void salvar() {
        if (altera) {
            try {
                usuarioDao.alterar(usuario);
                Messages.addGlobalInfo("Cargo " + usuario.getNome() + " Alterado com sucesso");
                usuario = new Usuario();
                selectAll();
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }

        } else {
            try {
                usuarioDao.salvar(usuario);
                Messages.addGlobalInfo("Cargo " + usuario.getNome() + " Cadastrado com sucesso");
                usuario = new Usuario();
                selectAll();
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }

        }
    }

    public void excluir() throws IOException {
        String msg;
        try {
            usuarioDao.excluir(usuarioSelecionado);
            msg = "Usuário " + usuarioSelecionado.getNome() + " Excluido com sucesso";
            usuarios.remove(usuarioSelecionado);

        } catch (Exception e) {
            msg = "Falha ao excluir" + usuarioSelecionado.getNome();
        }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCargoDao(Dao<Cargo> cargoDao) {
        this.cargoDao = cargoDao;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public boolean isAltera() {
        return altera;
    }

    public void setAltera(boolean altera) {
        this.altera = altera;
    }

}
