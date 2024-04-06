package www.artamonov.rest_task.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.repository.mapper.AuthorBookRepository;

@ExtendWith(MockitoExtension.class)
class AuthorBookServiceImplTest {
    private final AuthorBookRepository repository = Mockito.mock(AuthorBookRepository.class);
    private final AuthorBookServiceImpl service = new AuthorBookServiceImpl(repository);

    @Test
    void addAuthorBook() {
        service.addAuthorBook(1L, 1L);
        Mockito.verify(repository, Mockito.times(1)).addAuthorBook(1L, 1L);
        Mockito.verify(repository).addAuthorBook(1L, 1L);
    }

    @Test
    void removeAuthorBook() {
        service.removeAuthorBook(1L, 1L);
        Mockito.verify(repository, Mockito.times(1)).removeAuthorBook(1L, 1L);
        Mockito.verify(repository).removeAuthorBook(1L, 1L);
    }
}
