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
public class Servidor extends Computador {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Servico> servicos;

    private boolean hypervisor;

    private boolean fisico;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Servidor> vms;

    @ManyToOne
    private Servidor host;

    public Servidor() {
    }

    public Servidor(List<Servico> servicos, boolean hypervisor, boolean fisico, List<Servidor> vms, Servidor host) {
        this.servicos = servicos;
        this.hypervisor = hypervisor;
        this.fisico = fisico;
        this.vms = vms;
        this.host = host;
    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public boolean isHypervisor() {
        return hypervisor;
    }

    public void setHypervisor(boolean hypervisor) {
        this.hypervisor = hypervisor;
    }

    public List<Servidor> getVms() {
        return vms;
    }

    public void setVms(List<Servidor> vms) {
        this.vms = vms;
    }

    public Servidor getHost() {
        return host;
    }

    public void setHost(Servidor host) {
        this.host = host;
    }

    public boolean isFisico() {
        return fisico;
    }

    public void setFisico(boolean fisico) {
        this.fisico = fisico;
    }

}
