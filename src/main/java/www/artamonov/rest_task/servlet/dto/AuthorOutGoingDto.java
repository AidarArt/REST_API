package www.artamonov.rest_task.servlet.dto;

import java.util.List;

public class AuthorOutGoingDto {
    private Long id;
    private String name;
    private String surname;
    private List<BookOutGoingDto> books;

    public AuthorOutGoingDto() {
    }

    public AuthorOutGoingDto(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public List<BookOutGoingDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookOutGoingDto> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
