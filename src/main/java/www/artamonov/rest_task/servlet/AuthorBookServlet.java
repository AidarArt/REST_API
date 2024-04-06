package www.artamonov.rest_task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import www.artamonov.rest_task.db.PostgresConnectionManager;
import www.artamonov.rest_task.repository.impl.AuthorBookRepositoryImpl;
import www.artamonov.rest_task.service.impl.AuthorBookServiceImpl;
import www.artamonov.rest_task.service.mapper.AuthorBookService;

import java.io.IOException;

@WebServlet("/book/authors")
public class AuthorBookServlet extends HttpServlet {
    private final AuthorBookService service = new AuthorBookServiceImpl(
            new AuthorBookRepositoryImpl(new PostgresConnectionManager()));
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long bookId = Long.valueOf(req.getParameter("book_id"));
            Long authorId = Long.valueOf(req.getParameter("author_id"));
            service.addAuthorBook(bookId, authorId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long bookId = Long.valueOf(req.getParameter("book_id"));
            Long authorId = Long.valueOf(req.getParameter("author_id"));
            service.removeAuthorBook(bookId, authorId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
