package www.artamonov.rest_task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import www.artamonov.rest_task.model.BookEntity;
import www.artamonov.rest_task.model.PublishingHouseEntity;
import www.artamonov.rest_task.repository.impl.PublishingHouseRepositoryImpl;
import www.artamonov.rest_task.service.impl.PublishingHouseServiceImpl;
import www.artamonov.rest_task.service.mapper.PublishingHouseService;
import www.artamonov.rest_task.servlet.dto.BookOutGoingDto;
import www.artamonov.rest_task.servlet.dto.PublishingHouseIncomingDto;
import www.artamonov.rest_task.servlet.dto.PublishingHouseOutGoingDto;
import www.artamonov.rest_task.servlet.mapper.BookDtoMapper;
import www.artamonov.rest_task.servlet.mapper.PublishingHouseDtoMapper;
import www.artamonov.rest_task.servlet.mapper.impl.BookDtoMapperImpl;
import www.artamonov.rest_task.servlet.mapper.impl.PublishingHouseDtoMapperImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/publishing_houses")
public class PublishingHouseServlet extends HttpServlet {

    private final PublishingHouseService publishingHouseService = new PublishingHouseServiceImpl(new PublishingHouseRepositoryImpl());
    private final PublishingHouseDtoMapper publishingHouseDtoMapper = new PublishingHouseDtoMapperImpl();
    private final BookDtoMapper bookDtoMapper = new BookDtoMapperImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            if (req.getQueryString() == null) {
                List<PublishingHouseOutGoingDto> dtoList = new ArrayList<>();
                for (PublishingHouseEntity entity : publishingHouseService.getAll()) {
                    dtoList.add(publishingHouseDtoMapper.map(entity));
                }
                for (PublishingHouseOutGoingDto dto : dtoList) {
                    writer.write("id: " + dto.getId() + " name: " + dto.getName());
                    writer.write("\n");
                }
            } else {
                Long id = Long.valueOf(req.getParameter("id"));
                PublishingHouseEntity entity = publishingHouseService.getById(id);
                PublishingHouseOutGoingDto dto = publishingHouseDtoMapper.map(entity);
                List<BookEntity> books = publishingHouseService.getBooks(id);
                List<BookOutGoingDto> outGoingBooks = new ArrayList<>();
                writer.write("id: " + dto.getId() + ", name: " + dto.getName());
                writer.write("\nbooks:\n");
                for (BookEntity book : books) {
                    outGoingBooks.add(bookDtoMapper.map(book));
                }
                for (BookOutGoingDto bookDto : outGoingBooks) {
                    writer.write("{ id: " + bookDto.getId() + ", name: '" + bookDto.getName() + "', year: " + bookDto.getPublicationYear() + " }\n");
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PublishingHouseIncomingDto dto = new PublishingHouseIncomingDto();
        dto.setName(req.getParameter("name"));
        publishingHouseService.save(publishingHouseDtoMapper.map(dto));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PublishingHouseIncomingDto dto = new PublishingHouseIncomingDto();
        try {
            dto.setId(Long.valueOf(req.getParameter("id")));
            dto.setName(req.getParameter("name"));
            publishingHouseService.update(dto.getId(), publishingHouseDtoMapper.map(dto));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            publishingHouseService.delete(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }
}
