package www.artamonov.rest_task.repository.mapper;

public interface CrudRepository <T, K> {
    void create(T t);
    T findById(K id);
    void update(K id, T t);
    void delete(K id);
}
