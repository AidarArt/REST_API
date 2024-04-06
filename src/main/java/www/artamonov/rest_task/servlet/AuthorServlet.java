package www.artamonov.rest_task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.repository.impl.AuthorRepositoryImpl;
import www.artamonov.rest_task.service.impl.AuthorServiceImpl;
import www.artamonov.rest_task.service.mapper.AuthorService;
import www.artamonov.rest_task.servlet.dto.AuthorIncomingDto;
import www.artamonov.rest_task.servlet.dto.AuthorOutGoingDto;
import www.artamonov.rest_task.servlet.dto.BookOutGoingDto;
import www.artamonov.rest_task.servlet.mapper.AuthorDtoMapper;
import www.artamonov.rest_task.servlet.mapper.BookDtoMapper;
import www.artamonov.rest_task.servlet.mapper.impl.AuthorDtoMapperImpl;
import www.artamonov.rest_task.servlet.mapper.impl.BookDtoMapperImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {

    private final AuthorService authorService = new AuthorServiceImpl(new AuthorRepositoryImpl());
    private final AuthorDtoMapper dtoMapper = new AuthorDtoMapperImpl();
    private final BookDtoMapper bookDtoMapper = new BookDtoMapperImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            if (req.getQueryString() == null) {
                List<AuthorEntity> entities = authorService.getAll();
                List<AuthorOutGoingDto> dtoList = new ArrayList<>();
                for (AuthorEntity entity : entities) {
                    dtoList.add(dtoMapper.map(entity));
                }
                writer.write("Авторы:\n");
                for (AuthorOutGoingDto dto : dtoList) {
                    writer.write("id: " + dto.getId() + ", " + dto.getName() + " " + dto.getSurname() + "\n");
                }
            } else {
                Long id = Long.valueOf(req.getParameter("id"));
                AuthorEntity entity = authorService.getById(id);
                AuthorOutGoingDto dto = dtoMapper.map(entity);
                List< BookOutGoingDto> books = new ArrayList<>();
                for (BookEntity book : authorService.getBooks(id)) {
                    books.add(bookDtoMapper.map(book));
                }
                dto.setBooks(books);
                writer.write("id: " + dto.getId() + ", " + dto.getName() + " " + dto.getSurname() + "\n");
                writer.write("книги:\n");
                for (BookOutGoingDto bookDto : books) {
                    writer.write("id: " + bookDto.getId() + ", название: " + bookDto.getName() + ", год издания: " + bookDto.getPublicationYear() + ", издательство: " + bookDto.getPublishingHouse().getName() + "\n");
                }
            }


        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthorIncomingDto incomingDto = new AuthorIncomingDto();
        incomingDto.setName(req.getParameter("name"));
        incomingDto.setSurname(req.getParameter("surname"));
        authorService.save(dtoMapper.map(incomingDto));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AuthorIncomingDto incomingDto = new AuthorIncomingDto();
            incomingDto.setId(Long.valueOf(req.getParameter("id")));
            incomingDto.setName(req.getParameter("name"));
            incomingDto.setSurname(req.getParameter("surname"));
            authorService.update(incomingDto.getId(), dtoMapper.map(incomingDto));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AuthorIncomingDto incomingDto = new AuthorIncomingDto();
            incomingDto.setId(Long.valueOf(req.getParameter("id")));
            authorService.delete(incomingDto.getId());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
