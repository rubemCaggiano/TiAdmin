package br.com.kadesh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Computador extends Maquina {

    @ManyToOne
    private SistemaOperacional so;
    private boolean dominio;

    public Computador() {
    }

    public Computador(SistemaOperacional so, boolean dominio) {
        this.so = so;
        this.dominio = dominio;
    }

    public Computador(SistemaOperacional so, boolean dominio, int id, String ip, String mac, String nome, String observacao) {
        super(id, ip, mac, nome, observacao);
        this.so = so;
        this.dominio = dominio;
    }

    public SistemaOperacional getSo() {
        return so;
    }

    public void setSo(SistemaOperacional so) {
        this.so = so;
    }

    public boolean isDominio() {
        return dominio;
    }

    public void setDominio(boolean dominio) {
        this.dominio = dominio;
    }

}
