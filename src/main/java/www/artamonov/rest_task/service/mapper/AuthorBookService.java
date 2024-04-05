package www.artamonov.rest_task.service.mapper;

public interface AuthorBookService {
    void addAuthorBook(Long bookId, Long authorId);
    void removeAuthorBook(Long bookId, Long authorId);
}
