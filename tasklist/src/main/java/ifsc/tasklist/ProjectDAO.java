package ifsc.tasklist;

import java.util.List;

import javax.persistence.EntityManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectDAO implements DAO<Project>{
	private static ObservableList<Project> projects;
	
	@Override
	public Project get(String id) {
		if (projects != null)
			for (Project project : projects)
				if (project.getTitulo().contentEquals(id))
					return project;

		EntityManager entityMng = Conn.getEntityManager();
		Project project = entityMng.find(Project.class, id);
		entityMng.close();
		return project;
	}

	@Override
	public List<Project> getAll() {
		if (projects == null) {
			EntityManager entityMng = Conn.getEntityManager();
			projects = FXCollections.observableArrayList(entityMng.createQuery("select project from Project as project", Project.class).getResultList());
			entityMng.close();
		}
		return projects;
	}

	@Override
	public void add(Project project) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		entityMng.persist(project);
		entityMng.getTransaction().commit();
		entityMng.close();

		if (projects != null)
			projects.add(project);
	}

	@Override
	public void delete(Project proj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Project projectDB = entityMng.find(Project.class, proj.getTitulo());
		entityMng.remove(projectDB);
		entityMng.getTransaction().commit();
		entityMng.close();

		Project found = null;
		if (projects != null)
			for (Project project : projects)
				if (project.getTitulo().contentEquals(proj.getTitulo()))
					found = project;
		if(found != null)
			projects.remove(found);
	}

	@Override
	public void update(Project proj) {
		EntityManager entityMng = Conn.getEntityManager();
		entityMng.getTransaction().begin();
		Project projectDB = entityMng.find(Project.class, proj.getTitulo());
		projectDB.setObjetivo(proj.getObjetivo());
		entityMng.getTransaction().commit();
		entityMng.close();

		if (projects != null) {
			for (Project project: projects) {
				if (project.getTitulo().contentEquals(proj.getTitulo())) {
					project.setObjetivo(proj.getObjetivo());
				}
			}
		}
	}

}
