package www.artamonov.rest_task.model;

import java.util.List;
import java.util.Objects;

public class AuthorEntity {
    private Long id;
    private String name;
    private String surname;
    private List<BookEntity> books;

    public AuthorEntity() {
    }

    public AuthorEntity(Long id, String name, String surname, List<BookEntity> books) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity entity = (AuthorEntity) o;
        return Objects.equals(id, entity.id) && Objects.equals(name, entity.name) && Objects.equals(surname, entity.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
