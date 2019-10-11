package br.com.kadesh.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class AgendamentoBackup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_agendamento")
    @SequenceGenerator(name = "seq_agendamento", sequenceName = "seq_agendamento", initialValue = 1000)
    private int id;
    
    private String frequencia;
    
    @Temporal(TemporalType.TIME)
    private Date horario;
    
    private EnumTipoBackup tipo;
    private int qtdeCopias;
    private String tamanho;

    public AgendamentoBackup() {
    }

    public AgendamentoBackup(int id, String frequencia, Date horario, EnumTipoBackup tipo, int qtdeCopias, String tamanho) {
        this.id = id;
        this.frequencia = frequencia;
        this.horario = horario;
        this.tipo = tipo;
        this.qtdeCopias = qtdeCopias;
        this.tamanho = tamanho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public EnumTipoBackup getTipo() {
        return tipo;
    }

    public void setTipo(EnumTipoBackup tipo) {
        this.tipo = tipo;
    }

    public int getQtdeCopias() {
        return qtdeCopias;
    }

    public void setQtdeCopias(int qtdeCopias) {
        this.qtdeCopias = qtdeCopias;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

}
