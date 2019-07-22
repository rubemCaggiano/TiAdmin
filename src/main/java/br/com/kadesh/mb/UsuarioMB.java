package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Cargo;
import br.com.kadesh.model.Usuario;
import java.io.IOException;
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
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            usuario = usuarioDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            usuario = new Usuario();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                usuarioDao.salvar(usuario);
                Messages.addGlobalInfo("Cadastrado realizado com sucesso!");
                usuario = new Usuario();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/usuarios.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }

        } else {
            try {
                usuarioDao.alterar(usuario);
                Messages.addGlobalInfo("Cadastro alterado com sucesso!");
                usuario = new Usuario();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/usuarios.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }

        }
    }

    public void excluir() throws IOException {
        try {
            usuarioDao.excluir(usuarioSelecionado);
            Messages.addGlobalInfo("Cadastro excluido com sucesso");
            usuarios.remove(usuarioSelecionado);

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }
    }

    public void selecionar(Usuario u) {
        usuarioSelecionado = u;
    }

    public void deselecionar() {
        usuarioSelecionado = new Usuario();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEdicao() {
        editavel = false;
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

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
