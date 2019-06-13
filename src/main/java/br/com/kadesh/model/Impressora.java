package br.com.kadesh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Impressora extends Maquina {

    private String Serial;

    private String dns;

    private String local;

    private String gest;

    private boolean rede;
    
    @ManyToOne
    private Setor setor;

    @ManyToOne
    private ModeloImpressora modelo;

    public Impressora() {
    }

    public Impressora(String Serial, String dns, String local, String gest, boolean rede, Setor setor, ModeloImpressora modelo) {
        this.Serial = Serial;
        this.dns = dns;
        this.local = local;
        this.gest = gest;
        this.rede = rede;
        this.setor = setor;
        this.modelo = modelo;
    }

    public String getSerial() {
        return Serial;
    }

    public void setSerial(String Serial) {
        this.Serial = Serial;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getGest() {
        return gest;
    }

    public void setGest(String gest) {
        this.gest = gest;
    }

    public boolean isRede() {
        return rede;
    }

    public void setRede(boolean rede) {
        this.rede = rede;
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
