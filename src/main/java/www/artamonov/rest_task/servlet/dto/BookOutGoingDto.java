package www.artamonov.rest_task.servlet.dto;

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
}
