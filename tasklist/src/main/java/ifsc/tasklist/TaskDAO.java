package ifsc.tasklist;

import java.util.List;
import javax.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskDAO implements DAO<Task>{
	private static ObservableList<Task> tasks;

	@Override
	public Task get(String id) {
		if (tasks != null)
			for (Task task : tasks)
				if (task.getTitulo().contentEquals(id))
					return task;

		EntityManager entityMng = Conn.getEntityManager();
		Task task = entityMng.find(Task.class, id);
		entityMng.close();
		return task;
	}

	@Override
	public List<Task> getAll() {
		if (tasks == null) {
			EntityManager entityMng = Conn.getEntityManager();
			tasks = FXCollections.observableArrayList(
					entityMng.createQuery("select task from Task as task", Task.class).getResultList());
			entityMng.close();
		}
		return tasks;
	}

	@Override
	public void add(Task task) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(task);
		entityMng.getTransaction().commit();
		entityMng.close();

		if (tasks != null)
			tasks.add(task);
	}

	@Override
	public void delete(Task tks) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Task taskDB = entityMng.find(Task.class, tks.getTitulo());
		entityMng.remove(taskDB);
		entityMng.getTransaction().commit();
		entityMng.close();

		Task found = null;
		if (tasks != null)
			for (Task task1 : tasks)
				if (task1.getTitulo().contentEquals(tks.getTitulo()))
					found = task1;
		if(found != null)
			tasks.remove(found);
	}

	@Override
	public void update(Task tks) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Task taskDB = entityMng.find(Task.class, tks.getTitulo());
		taskDB.setDescricao(tks.getDescricao());
		taskDB.setData(tks.getData());
		entityMng.getTransaction().commit();
		entityMng.close();

		if (tasks != null) {
			for (Task task : tasks) {
				if (task.getTitulo().contentEquals(tks.getTitulo())) {
					task.setDescricao(tks.getDescricao());
					task.setData(tks.getData());
				}
			}
		}
	}

}