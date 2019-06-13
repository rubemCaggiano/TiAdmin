package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Impressora;
import br.com.kadesh.model.ModeloImpressora;
import br.com.kadesh.model.Setor;
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
public class ImpressoraMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private Dao<Impressora> impressoraDao = new GenericDAO<>(Impressora.class);
    private Dao<Setor> setorDao = new GenericDAO<>(Setor.class);
    private Dao<ModeloImpressora> modeloDao = new GenericDAO<>(ModeloImpressora.class);

    private List<Impressora> impressoras = new ArrayList<>();
    private List<Setor> setores = new ArrayList<>();
    private List<ModeloImpressora> modelos = new ArrayList<>();

    private Impressora impressora = new Impressora();
    private Impressora impressoraSelecionada = new Impressora();

    private Setor setor = new Setor();
    private ModeloImpressora modelo = new ModeloImpressora();

    private int id = 0;
    private boolean editavel;
    private boolean novo;

    public void init() {
        if (Faces.isAjaxRequest()) {
            return;
        }
        if (id != 0) {
            impressora = impressoraDao.buscarPorId(id);
            editavel = false;
            novo = false;
        } else {
            impressora = new Impressora();
            editavel = true;
            novo = true;
        }
    }

    public String salvar() {
        if (novo) {
            try {
                impressora.setDataCadastro(new Date());
                impressoraDao.salvar(impressora);
                Messages.addGlobalInfo("Impressora " + impressora.getNome() + " Cadastrado com sucesso");
                impressora = new Impressora();
                selectAll();

            } catch (Exception e) {
                Messages.addGlobalError("Falha ao cadastrar");
            }
        } else {
            try {
                impressora.setDataAlteracao(new Date());
                impressoraDao.alterar(impressora);
                Messages.addGlobalInfo("Impressora " + impressora.getNome() + " Alterado com sucesso");
                impressora = new Impressora();
                selectAll();

            } catch (Exception e) {
                Messages.addGlobalError("Falha ao alterar");
            }
        }
        return "impressoras.xhtml?faces-redirect=true";

    }

    public void excluir() {
        try {
            impressoraDao.excluir(impressoraSelecionada);
            impressoras.remove(impressoraSelecionada);
            Messages.addGlobalInfo("Impressora " + impressoraSelecionada.getNome() + " Excluido com sucesso");

        } catch (Exception e) {
            Messages.addGlobalError("Falha ao excluir");
        }

    }

    @PostConstruct
    public void selectAll() {
        impressoras = impressoraDao.buscarTodos(Impressora.class);
        setores = setorDao.buscarTodos(Setor.class);
        modelos = modeloDao.buscarTodos(ModeloImpressora.class);
    }

    public void selecionar(Impressora c) {
        impressoraSelecionada = c;
    }

    public void deselecionar() {
        impressoraSelecionada = new Impressora();
    }

    public void prepEdicao() {
        editavel = true;
    }

    public void cancelaEdicao() {
        editavel = false;
    }

//    Getters and Setters
//    --------------------------------------------------------------------------
    public List<Impressora> getImpressoras() {
        return impressoras;
    }

    public void setImpressoras(List<Impressora> impressoras) {
        this.impressoras = impressoras;
    }

    public Impressora getImpressora() {
        return impressora;
    }

    public void setImpressora(Impressora impressora) {
        this.impressora = impressora;
    }

    public Impressora getImpressoraSelecionada() {
        return impressoraSelecionada;
    }

    public void setImpressoraSelecionada(Impressora impressoraSelecionada) {
        this.impressoraSelecionada = impressoraSelecionada;
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

    public List<Setor> getSetores() {
        return setores;
    }

    public void setSetores(List<Setor> setores) {
        this.setores = setores;
    }

    public List<ModeloImpressora> getModelos() {
        return modelos;
    }

    public void setModelos(List<ModeloImpressora> modelos) {
        this.modelos = modelos;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public ModeloImpressora getModelo() {
        return modelo;
    }

    public void setModelo(ModeloImpressora modelo) {
        this.modelo = modelo;
    }

}
