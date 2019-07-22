package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Estacao;
import br.com.kadesh.model.Maquina;
import br.com.kadesh.model.Programa;
import br.com.kadesh.model.Setor;
import br.com.kadesh.model.SistemaOperacional;
import br.com.kadesh.model.Usuario;
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
public class EstacaoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Estacao> estacaoDao = new GenericDAO<>(Estacao.class);
    private Dao<Usuario> usuarioDao = new GenericDAO<>(Usuario.class);
    private Dao<SistemaOperacional> soDao = new GenericDAO<>(SistemaOperacional.class);
    private Dao<Setor> setorDao = new GenericDAO<>(Setor.class);
    private Dao<Programa> programaDao = new GenericDAO<>(Programa.class);

    private List<Maquina> maquinas = new ArrayList<>();
    private List<Estacao> estacoes = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Setor> setores = new ArrayList<>();
    private List<SistemaOperacional> sistemas = new ArrayList<>();
    private List<Programa> programas = new ArrayList<>();

    private Estacao estacao = new Estacao();
    private Estacao estacaoSelecionada = new Estacao();
    private Usuario usuario = new Usuario();
    private Setor setor = new Setor();
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
            estacao = estacaoDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            estacao = new Estacao();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                estacao.setDataCadastro(new Date());
                estacaoDao.salvar(estacao);
                Messages.addGlobalInfo("Cadastrado realizado com sucesso!");
                estacao = new Estacao();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/estacoes.jsf");

            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }
        } else {
            try {
                estacao.setDataAlteracao(new Date());
                estacaoDao.alterar(estacao);
                Messages.addGlobalInfo("Cadastro alterado com sucesso!");
                estacao = new Estacao();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/estacoes.jsf");

            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }

    }

    public void excluir() {
        try {
            estacaoDao.excluir(estacaoSelecionada);
            estacoes.remove(estacaoSelecionada);
            Messages.addGlobalInfo("Cadastro excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        estacoes = estacaoDao.buscarTodos(Estacao.class);
        usuarios = usuarioDao.buscarTodos(Usuario.class);
        setores = setorDao.buscarTodos(Setor.class);
        programas = programaDao.buscarTodos(Programa.class);
        sistemas = soDao.buscarTodos(SistemaOperacional.class);
    }

    public void selecionar(Estacao c) {
        estacaoSelecionada = c;
    }

    public void deselecionar() {
        estacaoSelecionada = new Estacao();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEdicao() {
        editavel = false;
    }

//    Getters and Setters 
//    ----------------------------------------------------------------------------        
    public Dao<Estacao> getEstacaoDao() {
        return estacaoDao;
    }

    public void setEstacaoDao(Dao<Estacao> estacaoDao) {
        this.estacaoDao = estacaoDao;
    }

    public Dao<Usuario> getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(Dao<Usuario> usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Dao<SistemaOperacional> getSoDao() {
        return soDao;
    }

    public void setSoDao(Dao<SistemaOperacional> soDao) {
        this.soDao = soDao;
    }

    public Dao<Setor> getSetorDao() {
        return setorDao;
    }

    public void setSetorDao(Dao<Setor> setorDao) {
        this.setorDao = setorDao;
    }

    public Dao<Programa> getProgramaDao() {
        return programaDao;
    }

    public void setProgramaDao(Dao<Programa> programaDao) {
        this.programaDao = programaDao;
    }

    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(List<Maquina> maquinas) {
        this.maquinas = maquinas;
    }

    public List<Estacao> getEstacoes() {
        return estacoes;
    }

    public void setEstacoes(List<Estacao> estacoes) {
        this.estacoes = estacoes;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
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

    public Estacao getEstacao() {
        return estacao;
    }

    public void setEstacao(Estacao estacao) {
        this.estacao = estacao;
    }

    public Estacao getEstacaoSelecionada() {
        return estacaoSelecionada;
    }

    public void setEstacaoSelecionada(Estacao estacaoSelecionada) {
        this.estacaoSelecionada = estacaoSelecionada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
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
