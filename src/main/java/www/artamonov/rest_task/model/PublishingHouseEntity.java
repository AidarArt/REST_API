package www.artamonov.rest_task.model;

import java.util.List;

public class PublishingHouseEntity {
    private Long id;
    private String name;
    private List<BookEntity> books;

    public PublishingHouseEntity() {
    }

    public PublishingHouseEntity(Long id, String name, List<BookEntity> books) {
        this.id = id;
        this.name = name;
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

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
