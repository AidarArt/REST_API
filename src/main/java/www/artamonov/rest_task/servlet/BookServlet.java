package www.artamonov.rest_task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.service.impl.BookServiceImpl;
import www.artamonov.rest_task.service.mapper.BookService;
import www.artamonov.rest_task.servlet.dto.AuthorOutGoingDto;
import www.artamonov.rest_task.servlet.dto.BookIncomingDto;
import www.artamonov.rest_task.servlet.dto.BookOutGoingDto;
import www.artamonov.rest_task.servlet.mapper.AuthorDtoMapper;
import www.artamonov.rest_task.servlet.mapper.BookDtoMapper;
import www.artamonov.rest_task.servlet.mapper.impl.AuthorDtoMapperImpl;
import www.artamonov.rest_task.servlet.mapper.impl.BookDtoMapperImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private final BookService bookService = new BookServiceImpl();
    private final BookDtoMapper bookDtoMapper = new BookDtoMapperImpl();
    private final AuthorDtoMapper authorDtoMapper = new AuthorDtoMapperImpl();
    private static final String WRITING_INFO = "id: %s, название: %s, год издания: %s\n Издательство: %s\n";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            if (req.getQueryString() == null) {
                List<BookOutGoingDto> dtoList = new ArrayList<>();
                for (BookEntity entity : bookService.getAll()) {
                    dtoList.add(bookDtoMapper.map(entity));
                }
                for (BookOutGoingDto dto : dtoList) {
                    writer.write(String.format(WRITING_INFO, dto.getId(), dto.getName(), dto.getPublicationYear(), dto.getPublishingHouse().getName()));
                }
            } else {
                Long id = Long.valueOf(req.getParameter("id"));
                BookEntity entity = bookService.getById(id);
                List<AuthorOutGoingDto> authors = new ArrayList<>();
                BookOutGoingDto bookDto = bookDtoMapper.map(entity);
                for (AuthorEntity author : bookService.getAuthors(id)) {
                    authors.add(authorDtoMapper.map(author));
                }
                writer.write("Книга:\n");
                writer.write(String.format(WRITING_INFO, bookDto.getId(), bookDto.getName(), bookDto.getPublicationYear(), bookDto.getPublishingHouse().getName()));
                writer.write("Авторы: \n");
                for (AuthorOutGoingDto dto : authors) {
                    writer.write("{ id: " + dto.getId() + ", " + dto.getName() + " " + dto.getSurname() + " }\n");
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookIncomingDto incomingDto = new BookIncomingDto();
        try {
            incomingDto.setName(req.getParameter("name"));
            incomingDto.setPublicationYear(Integer.parseInt(req.getParameter("year")));
            incomingDto.setPublishingHouse(new PublishingHouseEntity());
            incomingDto.getPublishingHouse().setId(Long.valueOf(req.getParameter("ph_id")));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookIncomingDto incomingDto = new BookIncomingDto();
        try {
            incomingDto.setId(Long.valueOf(req.getParameter("id")));
            incomingDto.setName(req.getParameter("name"));
            incomingDto.setPublicationYear(Integer.parseInt(req.getParameter("year")));
            incomingDto.setPublishingHouse(new PublishingHouseEntity());
            incomingDto.getPublishingHouse().setId(Long.valueOf(req.getParameter("ph_id")));
            bookService.update(incomingDto.getId(), bookDtoMapper.map(incomingDto));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookIncomingDto incomingDto = new BookIncomingDto();
        try {
            incomingDto.setId(Long.valueOf(req.getParameter("id")));
            bookService.delete(incomingDto.getId());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
