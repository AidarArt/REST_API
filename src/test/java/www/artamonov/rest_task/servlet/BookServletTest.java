package www.artamonov.rest_task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.service.mapper.BookService;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BookServletTest {
    private final BookServlet servlet = new BookServlet();
    private final BookService service = Mockito.mock(BookService.class);
    private final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private final HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException, IOException {

        PrintWriter writer = Mockito.mock(PrintWriter.class);
        Mockito.when(response.getWriter()).thenReturn(writer);

        Field field = servlet.getClass().getDeclaredField("bookService");
        field.setAccessible(true);
        field.set(servlet, service);
    }

    @Test
    void doGetWithoutParameter() throws ServletException, IOException {
        Mockito.when(request.getQueryString()).thenReturn(null);
        List<BookEntity> entities = new ArrayList<>();
        BookEntity entity = new BookEntity();
        entity.setPublishingHouse(new PublishingHouseEntity());
        entities.add(entity);
        Mockito.when(service.getAll()).thenReturn(entities);
        servlet.doGet(request, response);
        Mockito.verify(service, Mockito.times(1)).getAll();
        Mockito.verify(request, Mockito.never()).getParameter("id");
    }

    @Test
    void doGetWithParameterId() throws ServletException, IOException {
        Mockito.when(request.getQueryString()).thenReturn("id=1");
        Mockito.when(request.getParameter("id")).thenReturn("1");

        BookEntity entity = new BookEntity();
        entity.setPublishingHouse(new PublishingHouseEntity());
        Mockito.when(service.getById(1L)).thenReturn(entity);

        List<AuthorEntity> authors = new ArrayList<>();
        authors.add(new AuthorEntity());
        Mockito.when(service.getAuthors(1L)).thenReturn(authors);

        servlet.doGet(request, response);

        Mockito.verify(request, Mockito.times(1)).getParameter("id");
        Mockito.verify(service, Mockito.times(1)).getById(1L);
        Mockito.verify(service, Mockito.times(1)).getAuthors(1L);
    }

    @Test
    void doPost() throws ServletException, IOException {
        Mockito.when(request.getParameter("name")).thenReturn("name");
        Mockito.when(request.getParameter("year")).thenReturn("1111");
        Mockito.when(request.getParameter("ph_id")).thenReturn("1");

        servlet.doPost(request, response);

        BookEntity entity = new BookEntity();
        entity.setName("name");
        entity.setPublicationYear(1111);
        entity.setPublishingHouse(new PublishingHouseEntity());
        entity.getPublishingHouse().setId(1L);

        Mockito.verify(service, Mockito.times(1)).save(entity);
    }

    @Test
    void doPut() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn("1");
        Mockito.when(request.getParameter("name")).thenReturn("name");
        Mockito.when(request.getParameter("year")).thenReturn("1111");
        Mockito.when(request.getParameter("ph_id")).thenReturn("1");

        servlet.doPut(request, response);

        BookEntity entity = new BookEntity();
        entity.setId(1L);
        entity.setName("name");
        entity.setPublicationYear(1111);
        entity.setPublishingHouse(new PublishingHouseEntity());
        entity.getPublishingHouse().setId(1L);

        Mockito.verify(service, Mockito.times(1)).update(1L, entity);
    }

    @Test
    void doDelete() throws ServletException, IOException {
        Mockito.when(request.getParameter("id")).thenReturn("1");
        servlet.doDelete(request, response);
        Mockito.verify(service, Mockito.times(1)).delete(1L);
    }
}
