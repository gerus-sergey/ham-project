package services;

import java.util.List;

public interface CRUDService<T, V> {

    T addOrUpdate(T obj);

    List<T> getAll();

    void delete(V id);

    T get(V id);
}
