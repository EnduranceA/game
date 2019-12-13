package repositories;

import models.Word;

public interface WordRepository extends CrudRepository<Word> {
    boolean isExist(String name);
}
