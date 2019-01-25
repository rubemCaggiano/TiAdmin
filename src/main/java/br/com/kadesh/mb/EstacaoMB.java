package br.com.kadesh.mb;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Estacao;
import br.com.kadesh.model.Maquina;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class EstacaoMB {

    private Dao<Estacao> estacaoDao = new GenericDAO<>(Estacao.class);

    private List<Maquina> maquinas;
    private List<Estacao> estacoes;

    public EstacaoMB() {
        maquinas = new ArrayList<>();
        estacoes = new ArrayList<>();
    }

    public void selectAll() {
        estacoes = estacaoDao.buscarTodos(Estacao.class);
    }

    public Dao<Estacao> getEstacaoDao() {
        return estacaoDao;
    }

    public void setEstacaoDao(Dao<Estacao> estacaoDao) {
        this.estacaoDao = estacaoDao;
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

}
