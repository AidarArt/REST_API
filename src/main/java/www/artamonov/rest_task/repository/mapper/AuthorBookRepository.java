package www.artamonov.rest_task.repository.mapper;

public interface AuthorBookRepository {
    void addAuthorBook(Long bookId, Long authorId);
    void removeAuthorBook(Long bookId, Long authorId);
}
