package www.artamonov.rest_task.servlet.dto;

import www.artamonov.rest_task.model.PublishingHouseEntity;

public class BookIncomingDto {
    private Long id;
    private String name;
    private int publicationYear;
    private PublishingHouseEntity publishingHouse;

    public BookIncomingDto() {
    }

    public BookIncomingDto(Long id, String name, int publicationYear, PublishingHouseEntity publishingHouse) {
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

    public PublishingHouseEntity getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouseEntity publishingHouse) {
        this.publishingHouse = publishingHouse;
    }
}
