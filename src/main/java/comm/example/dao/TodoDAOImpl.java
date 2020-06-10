package comm.example.dao;

import comm.example.model.Todo;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository("todoDAO")
public class TodoDAOImpl implements TodoDAO {
    private EntityManager entityManager;

    @Autowired
    public TodoDAOImpl(EntityManager entityManager) {

        this.entityManager = entityManager;

    }

    public TodoDAOImpl() {
        super();
    }

    @Override
    @Transactional
    public List<Todo> getTodos() {
        Query query = entityManager.createQuery("from Customer");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void createTodo(Todo todo) {
        Session session = entityManager.unwrap(org.hibernate.Session.class);
        //  session.getTransaction().begin();
        session.saveOrUpdate(todo);
        //session.getTransaction().commit();
    }

    @Override
    @Transactional
    public Todo getTodo(int todoId) {
        return entityManager.find(Todo.class,todoId);
    }

    @Override
    @Transactional
    public void deleteTodo(int todoId) {
        entityManager.remove(getTodo(todoId));
    }


}
