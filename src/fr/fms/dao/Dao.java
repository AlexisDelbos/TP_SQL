package fr.fms.dao;

import java.sql.Connection;
import java.util.logging.Logger;

public interface Dao<T> {
	
	public Connection connection = BddConnection.getConnection();
	public static final Logger logger = Logger.getLogger(Dao.class.getName());
	public void create(T entity);
	public T read(int id);
	public void update( String columnName,Object newValue, int id);
	public void delete(int id);
	
}
