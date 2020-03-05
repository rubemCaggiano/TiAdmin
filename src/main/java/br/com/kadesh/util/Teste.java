
package br.com.kadesh.util;

import br.com.kadesh.dao.Dao;
import br.com.kadesh.dao.GenericDAO;
import br.com.kadesh.model.Cargo;
import br.com.kadesh.model.Produto;
import br.com.kadesh.model.Usuario;
import java.util.List;


public class Teste {

    public static void main(String[] args) {
//        Dao<Cargo> cargoDao = new GenericDAO<>(Cargo.class);
        Dao<Usuario> dao = new GenericDAO<>(Usuario.class);
        
        Usuario u = dao.buscarPorId(1);
        List<Usuario> us = dao.buscarTodos(Usuario.class);
        System.out.println(us.get(0).getNome());
        
        

    }
    
}
