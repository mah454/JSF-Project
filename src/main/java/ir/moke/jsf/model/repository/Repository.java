package ir.moke.jsf.model.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class Repository<T> implements GenericRepository<T> {

    Logger LOGGER = LoggerFactory.getLogger(Repository.class);

    @PersistenceContext(name = "db-connection")
    protected EntityManager entityManager;

    private Class<T> entityClass;

    public Repository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Repository() {
    }

    public void insert(T t) {
        entityManager.persist(t);
    }

    public void update(T t) {
        entityManager.merge(t);
    }

    public void delete(long id) {
        entityManager.createQuery("DELETE " + entityClass.getName() + " u where u.id=:x").setParameter("x", id).executeUpdate();
    }

    public void delete(T t) {
        entityManager.remove(t);
    }

    public T select(long id) {
        return entityManager.find(entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> select() {
        return entityManager.createQuery("FROM " + entityClass.getName()).getResultList();
    }
}
