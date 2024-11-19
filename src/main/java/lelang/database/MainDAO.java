package lelang.database;

import java.util.List;

public interface MainDAO<T> {
    T findById(long id);
    List<T> findAll();
    void create(T entity);
    void update(T entity);
    void delete(long id);
}