package repositories;

import java.util.List;

public interface CrudRepository<T> {
    void save(T t);
    void delete(Integer id);
    List<T> findAll();
}
