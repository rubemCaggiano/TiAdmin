package br.com.kadesh.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Backup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_backup")
    @SequenceGenerator(name = "seq_backup", sequenceName = "seq_backup", initialValue = 1000)
    private int id;

    private String nome;
    private String localOrigem;
    private String localDestino;

    @ManyToOne
    private Servidor execucao;

    @ManyToOne
    private Servico tipoExecucao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<AgendamentoBackup> agendamentos;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> exclusoes;

    private String observacoes;

    public Backup() {
    }

    public Backup(int id, String nome, String localOrigem, String localDestino, Servidor execucao, Servico tipoExecucao, List<AgendamentoBackup> agendamentos, List<String> exclusoes, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.execucao = execucao;
        this.tipoExecucao = tipoExecucao;
        this.agendamentos = agendamentos;
        this.exclusoes = exclusoes;
        this.observacoes = observacoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(String localOrigem) {
        this.localOrigem = localOrigem;
    }

    public String getLocalDestino() {
        return localDestino;
    }

    public void setLocalDestino(String localDestino) {
        this.localDestino = localDestino;
    }

    public Servidor getExecucao() {
        return execucao;
    }

    public void setExecucao(Servidor execucao) {
        this.execucao = execucao;
    }

    public List<AgendamentoBackup> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<AgendamentoBackup> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<String> getExclusoes() {
        return exclusoes;
    }

    public void setExclusoes(List<String> exclusoes) {
        this.exclusoes = exclusoes;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Servico getTipoExecucao() {
        return tipoExecucao;
    }

    public void setTipoExecucao(Servico tipoExecucao) {
        this.tipoExecucao = tipoExecucao;
    }

}
