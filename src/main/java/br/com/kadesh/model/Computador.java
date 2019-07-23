package br.com.kadesh.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Computador extends Maquina {

    @ManyToOne
    private SistemaOperacional so;

    private boolean dominio;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Programa> programasInstalados;

    public Computador() {
    }

    public Computador(SistemaOperacional so, boolean dominio, List<Programa> programasInstalados) {
        this.so = so;
        this.dominio = dominio;
        this.programasInstalados = programasInstalados;
    }

    public Computador(SistemaOperacional so, boolean dominio, List<Programa> programasInstalados, int id, String ip, String mac, String nome, String observacao) {
        super(id, ip, mac, nome, observacao);
        this.so = so;
        this.dominio = dominio;
        this.programasInstalados = programasInstalados;
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

    public List<Programa> getProgramasInstalados() {
        return programasInstalados;
    }

    public void setProgramasInstalados(List<Programa> programasInstalados) {
        this.programasInstalados = programasInstalados;
    }

}
