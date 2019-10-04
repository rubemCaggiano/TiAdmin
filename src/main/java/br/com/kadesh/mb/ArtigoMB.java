package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Artigo;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
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
public class ArtigoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Artigo> artigoDao = new GenericDAO<>(Artigo.class);

    private List<Artigo> artigos = new ArrayList<>();

    private Artigo artigo = new Artigo();
    private Artigo artigoSelecionado = new Artigo();

    private int id = 0;
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            artigo = artigoDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            artigo = new Artigo();
            editavel = true;
            novo = true;
        }
    }

    public void salvar() {
        if (novo) {
            try {
                artigoDao.salvar(artigo);
                Messages.addGlobalInfo("Cadastrado realizado com sucesso!");
                artigo = new Artigo();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/artigos.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }

        } else {
            try {
                artigoDao.alterar(artigo);
                Messages.addGlobalInfo("Cadastro alterado com sucesso!");
                artigo = new Artigo();
                selectAll();
                Faces.getFlash().setKeepMessages(true);
                Faces.redirect("/tiadmin/Telas/artigos.jsf");
            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }
    }

    public void excluir() {
        try {
            artigoDao.excluir(artigoSelecionado);
            artigos.remove(artigoSelecionado);
            Messages.addGlobalInfo("Cadastro excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        artigos = artigoDao.buscarTodos(Artigo.class);
    }

    public void selecionar(Artigo c) {
        artigoSelecionado = c;
    }

    public void deselecionar() {
        artigoSelecionado = new Artigo();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEditavel() {
        editavel = false;
    }
//------------------------------------------------------------------------------

    public List<Artigo> getArtigos() {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos) {
        this.artigos = artigos;
    }

    public Artigo getArtigo() {
        return artigo;
    }

    public void setArtigo(Artigo artigo) {
        this.artigo = artigo;
    }

    public Artigo getArtigoSelecionado() {
        return artigoSelecionado;
    }

    public void setArtigoSelecionado(Artigo artigoSelecionado) {
        this.artigoSelecionado = artigoSelecionado;
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
