
package br.com.kadesh.util;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Cargo;


public class Teste {

    public static void main(String[] args) {
        Dao<Cargo> cargoDao = new GenericDAO<>(Cargo.class);
        
        Cargo c = new Cargo(0, "Vendedor");
        
        cargoDao.salvar(c);
    }
    
}
