package br.com.kadesh.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author valter
 * @param <T>
 */
public interface Dao<T> {

    public T buscarPorId(Serializable id);

    public void salvar(T t);

    public void alterar(T t);
    
    public void excluir(T t);
    
    public List<T> buscarTodos(Class<T> clazz);
    
    
}
