package interfaces;

import java.util.List;

public interface Service<T> {
    List<T> findAll();
    void save(T object);
}
