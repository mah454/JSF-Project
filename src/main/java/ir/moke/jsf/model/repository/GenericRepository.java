package ir.moke.jsf.model.repository;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GenericRepository<T> {
    void insert(T t) ;

    void update(T t);

    void delete(long id);

    void delete(T t);

    T select(long id);

    List<T> select();
}
