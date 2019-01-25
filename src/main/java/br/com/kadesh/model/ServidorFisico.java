package br.com.kadesh.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class ServidorFisico extends Servidor {

    private boolean hypervisor;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ServidorVirtual> vms;

    public ServidorFisico() {
    }

    public ServidorFisico(boolean hypervisor, List<ServidorVirtual> vms) {
        this.hypervisor = hypervisor;
        this.vms = vms;
    }

    public boolean isHypervisor() {
        return hypervisor;
    }

    public void setHypervisor(boolean hypervisor) {
        this.hypervisor = hypervisor;
    }

    public List<ServidorVirtual> getVms() {
        return vms;
    }

    public void setVms(List<ServidorVirtual> vms) {
        this.vms = vms;
    }

}
