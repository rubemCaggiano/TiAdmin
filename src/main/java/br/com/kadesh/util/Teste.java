
package br.com.kadesh.util;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Cargo;
import br.com.kadesh.model.Produto;


public class Teste {

    public static void main(String[] args) {
//        Dao<Cargo> cargoDao = new GenericDAO<>(Cargo.class);
        Dao<Produto> dao = new GenericDAO<>(Produto.class);
        
        Produto p = new Produto(0, "Teste");
        
//        Cargo c = new Cargo(0, "Vendedor");
        
//        cargoDao.salvar(c);

            dao.salvar(p);
    }
    
}
