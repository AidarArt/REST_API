package www.artamonov.rest_task.service.impl;

import www.artamonov.rest_task.repository.mapper.AuthorBookRepository;
import www.artamonov.rest_task.service.mapper.AuthorBookService;

public class AuthorBookServiceImpl implements AuthorBookService {
    private final AuthorBookRepository repository;

    public AuthorBookServiceImpl(AuthorBookRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addAuthorBook(Long bookId, Long authorId) {
        repository.addAuthorBook(bookId, authorId);
    }

    @Override
    public void removeAuthorBook(Long bookId, Long authorId) {
        repository.removeAuthorBook(bookId, authorId);
    }
}
