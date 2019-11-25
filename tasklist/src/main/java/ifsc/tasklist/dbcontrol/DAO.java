package ifsc.tasklist.dbcontrol;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

public interface DAO<T> {
	
	public T get(String id);
	
	public List<T> getAll() throws UnknownHostException, IOException;
	
	public void add(T obj);
	
	public void delete(T obj);
	
	public void update(T obj);
}
