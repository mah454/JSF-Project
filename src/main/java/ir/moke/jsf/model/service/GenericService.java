package ir.moke.jsf.model.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GenericService<T> {
    void save(T t);

    void modify(T t);

    void remove(long id);

    void remove(T t);

    boolean isExist(String str);

    boolean isExist(long id);

    T find(long id);

    T find(String str);

    List<T> findAll();

}
