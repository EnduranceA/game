package repositories;

import models.MainWord;

public interface MainWordRepository extends CrudRepository<MainWord> {
    boolean isExist(Integer id);
}
