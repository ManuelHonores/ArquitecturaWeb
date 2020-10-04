package paqueteREST;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, ID extends Serializable> {
	
	public T persist(T entity);
	
	public T findById(ID id);
	
	public List<T> findAll();
}
