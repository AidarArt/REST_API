package www.artamonov.rest_task.model;

import java.util.List;
import java.util.Objects;

public class BookEntity {
    private Long id;
    private String name;
    private int publicationYear;
    private PublishingHouseEntity publishingHouse;
    private List<AuthorEntity> authors;

    public BookEntity() {
    }

    public BookEntity(Long id, String name, int publicationYear, PublishingHouseEntity publishingHouse) {
        this.id = id;
        this.name = name;
        this.publicationYear = publicationYear;
        this.publishingHouse = publishingHouse;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity entity = (BookEntity) o;
        return publicationYear == entity.publicationYear && Objects.equals(id, entity.id) && Objects.equals(name, entity.name) && Objects.equals(publishingHouse, entity.publishingHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publicationYear, publishingHouse);
    }
}
