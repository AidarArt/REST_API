package www.artamonov.rest_task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.service.mapper.AuthorBookService;
import www.artamonov.rest_task.servlet.AuthorBookServlet;

import java.io.IOException;
import java.lang.reflect.Field;

@ExtendWith(MockitoExtension.class)
class AuthorBookServletTest {

    private final AuthorBookServlet servlet = new AuthorBookServlet();
    private final AuthorBookService service = Mockito.mock(AuthorBookService.class);
    private final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    private final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field field = servlet.getClass().getDeclaredField("service");
        field.setAccessible(true);
        field.set(servlet, service);
    }

    @Test
    void doPost() throws ServletException, IOException {
        Mockito.when(request.getParameter("book_id")).thenReturn("1");
        Mockito.when(request.getParameter("author_id")).thenReturn("1");
        servlet.doPost(request, response);
        Mockito.verify(service, Mockito.times(1)).addAuthorBook(1L, 1L);
    }

    @Test
    void doDelete() throws ServletException, IOException {
        Mockito.when(request.getParameter("book_id")).thenReturn("1");
        Mockito.when(request.getParameter("author_id")).thenReturn("1");
        servlet.doDelete(request, response);
        Mockito.verify(service, Mockito.times(1)).removeAuthorBook(1L, 1L);
    }
}
