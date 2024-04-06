package www.artamonov.rest_task.servlet.dto;

import java.util.Objects;

public class BookOutGoingDto {
    private Long id;
    private String name;
    private int publicationYear;
    private PublishingHouseOutGoingDto publishingHouse;

    public BookOutGoingDto() {
    }

    public BookOutGoingDto(Long id, String name, int publicationYear, PublishingHouseOutGoingDto publishingHouse) {
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

    public PublishingHouseOutGoingDto getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouseOutGoingDto publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOutGoingDto that = (BookOutGoingDto) o;
        return publicationYear == that.publicationYear && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(publishingHouse, that.publishingHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publicationYear, publishingHouse);
    }
}
