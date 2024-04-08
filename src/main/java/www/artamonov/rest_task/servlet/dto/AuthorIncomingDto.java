package www.artamonov.rest_task.servlet.dto;

public class AuthorIncomingDto {
    private Long id;
    private String name;
    private String surname;

    public AuthorIncomingDto() {
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
