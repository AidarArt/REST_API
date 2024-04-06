package www.artamonov.rest_task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.service.mapper.AuthorService;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AuthorServletTest {

    private final AuthorServlet servlet = new AuthorServlet();
    private final AuthorService service = Mockito.mock(AuthorService.class);
    private final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException, IOException {
        List<AuthorEntity> entities = new ArrayList<>();
        entities.add(new AuthorEntity());

        Mockito.when(service.getAll()).thenReturn(entities);
        Mockito.when(service.getById(1L)).thenReturn(new AuthorEntity());

        PrintWriter writer = Mockito.mock(PrintWriter.class);

        Mockito.when(response.getWriter()).thenReturn(writer);

        Field field = servlet.getClass().getDeclaredField("authorService");
        field.setAccessible(true);
        field.set(servlet, service);
    }

    @Test
    void doGetWithoutParameter() throws ServletException, IOException {
        Mockito.when(request.getQueryString()).thenReturn(null);
        servlet.doGet(request, response);
        Mockito.verify(request, Mockito.never()).getParameter("id");
        Mockito.verify(service, Mockito.times(1)).getAll();
    }

    @Test
    void doGetWithParameterId() throws ServletException, IOException {
        List<BookEntity> entities = new ArrayList<>();
        BookEntity entity = new BookEntity();
        entity.setPublishingHouse(new PublishingHouseEntity());
        entities.add(entity);
        Mockito.when(service.getBooks(1L)).thenReturn(entities);
        Mockito.when(request.getQueryString()).thenReturn("id=1");
        Mockito.when(request.getParameter("id")).thenReturn("1");
        servlet.doGet(request, response);
        Mockito.verify(request, Mockito.times(1)).getParameter("id");
        Mockito.verify(service, Mockito.times(1)).getById(1L);
        Mockito.verify(service, Mockito.times(1)).getBooks(1L);
    }

    @Test
    void doPost() throws ServletException, IOException {
        Mockito.when(request.getParameter("name")).thenReturn("name");
        Mockito.when(request.getParameter("surname")).thenReturn("surname");
        servlet.doPost(request, response);
        AuthorEntity entity = new AuthorEntity();
        entity.setName("name");
        entity.setSurname("surname");
        Mockito.verify(service, Mockito.times(1)).save(entity);
    }

    @Test
    void doPut() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn("1");
        Mockito.when(request.getParameter("name")).thenReturn("name");
        Mockito.when(request.getParameter("surname")).thenReturn("surname");
        servlet.doPut(request, response);
        AuthorEntity entity = new AuthorEntity();
        entity.setName("name");
        entity.setSurname("surname");
        Mockito.verify(service, Mockito.times(1)).update(1L, entity);
    }

    @Test
    void doDelete() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn("1");
        servlet.doDelete(request, response);
        Mockito.verify(service, Mockito.times(1)).delete(1L);
    }
}
