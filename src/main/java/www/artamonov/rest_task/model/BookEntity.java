package www.artamonov.rest_task.model;

import java.util.List;

public class BookEntity {
    private Long id;
    private String name;
    private int publicationYear;
    private PublishingHouseEntity publishingHouse;
    private List<AuthorEntity> authors;

    public BookEntity() {
    }

    public BookEntity(Long id, String name, int publicationYear, PublishingHouseEntity publishingHouse, List<AuthorEntity> authors) {
        this.id = id;
        this.name = name;
        this.publicationYear = publicationYear;
        this.publishingHouse = publishingHouse;
        this.authors = authors;
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

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    public PublishingHouseEntity getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouseEntity publishingHouse) {
        this.publishingHouse = publishingHouse;
    }
}
