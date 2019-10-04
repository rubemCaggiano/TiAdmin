package br.com.kadesh.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Artigo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_artigo")
    @SequenceGenerator(name = "seq_artigo", sequenceName = "seq_artigo", initialValue = 1000)
    private int id;

    private String titulo;

    private String descricao;

    private String chaves;

    public Artigo() {
    }

    public Artigo(int id, String titulo, String descricao, String chaves) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.chaves = chaves;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Artigo other = (Artigo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getChaves() {
        return chaves;
    }

    public void setChaves(String chaves) {
        this.chaves = chaves;
    }

}
