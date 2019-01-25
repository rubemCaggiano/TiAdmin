package br.com.kadesh.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class ServidorVirtual extends Servidor {

    @ManyToOne
    private ServidorFisico host;

    public ServidorVirtual() {
    }

    public ServidorVirtual(ServidorFisico host) {
        this.host = host;
    }

    public ServidorFisico getHost() {
        return host;
    }

    public void setHost(ServidorFisico host) {
        this.host = host;
    }

}
