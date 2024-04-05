package www.artamonov.rest_task.servlet.dto;

public class BookOutGoingDto {
    private Long id;
    private String name;
    private int publicationYear;

    public BookOutGoingDto() {
    }

    public BookOutGoingDto(Long id, String name, int publicationYear) {
        this.id = id;
        this.name = name;
        this.publicationYear = publicationYear;
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
}
