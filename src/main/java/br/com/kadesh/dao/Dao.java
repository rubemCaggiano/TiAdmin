package br.com.kadesh.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;

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
