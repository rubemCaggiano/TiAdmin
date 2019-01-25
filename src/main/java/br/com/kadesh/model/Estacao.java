package br.com.kadesh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Estacao extends Computador {

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Setor setor;

    public Estacao() {
    }

    public Estacao(Usuario usuario, Setor setor) {
        this.usuario = usuario;
        this.setor = setor;
    }

    public Estacao(Usuario usuario, Setor setor, SistemaOperacional so, boolean dominio) {
        super(so, dominio);
        this.usuario = usuario;
        this.setor = setor;
    }

    public Estacao(Usuario usuario, Setor setor, SistemaOperacional so, boolean dominio, int id, String ip, String mac, String nome, String observacao) {
        super(so, dominio, id, ip, mac, nome, observacao);
        this.usuario = usuario;
        this.setor = setor;
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

}
