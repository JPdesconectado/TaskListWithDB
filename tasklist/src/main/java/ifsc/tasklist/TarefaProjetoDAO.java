package ifsc.tasklist;

import java.util.List;

import javax.persistence.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TarefaProjetoDAO implements DAO<TarefaProjeto>{
	private static ObservableList<TarefaProjeto> tp;

@Override
public TarefaProjeto get(String id) {
	if (tp!= null)
		for (TarefaProjeto tafproj : tp)
			if (tafproj.getTitulo().contentEquals(id))
				return tafproj;

	EntityManager entityMng = Conn.getEntityManager();
	TarefaProjeto tafproj = entityMng.find(TarefaProjeto.class, id);
	entityMng.close();
	return tafproj;
}

@Override
public List<TarefaProjeto> getAll() {
	if (tp == null) {
		EntityManager entityMng = Conn.getEntityManager();
		tp = FXCollections.observableArrayList(entityMng.createQuery("select tarefaprojeto from TarefaProjeto as tarefaprojeto", TarefaProjeto.class).getResultList());
		entityMng.close();
	}
	return tp;
}

@Override
public void add(TarefaProjeto obj) {
	EntityManager entityMng = Conn.getEntityManager();
	entityMng.getTransaction().begin();
	entityMng.persist(obj);
	entityMng.getTransaction().commit();
	entityMng.close();

	if (tp != null)
		tp.add(obj);
	
}

@Override
public void delete(TarefaProjeto obj) {
	EntityManager entityMng = Conn.getEntityManager();
	entityMng.getTransaction().begin();
	TarefaProjeto tpDB = entityMng.find(TarefaProjeto.class, obj.getTitulo());
	entityMng.remove(tpDB);
	entityMng.getTransaction().commit();
	entityMng.close();

	TarefaProjeto found = null;
	if (tp != null)
		for (TarefaProjeto tafproj : tp)
			if (tafproj.getTitulo().contentEquals(obj.getTitulo()))
				found = tafproj;
	if(found != null)
		tp.remove(found);
}

@Override
public void update(TarefaProjeto obj) {
	EntityManager entityMng = Conn.getEntityManager();
	entityMng.getTransaction().begin();
	TarefaProjeto tpDB = entityMng.find(TarefaProjeto.class, obj.getTitulo());
	tpDB.setDescricao(obj.getDescricao());
	tpDB.setData(obj.getData());
	tpDB.setProjeto(obj.getProjeto());
	entityMng.getTransaction().commit();
	entityMng.close();

	if (tp != null) {
		for (TarefaProjeto tpj : tp) {
			if (tpj.getTitulo().contentEquals(obj.getTitulo())) {
				tpj.setDescricao(obj.getDescricao());
				tpj.setData(obj.getData());
				tpj.setProjeto(obj.getProjeto());
			}
		}
	}
}


}
