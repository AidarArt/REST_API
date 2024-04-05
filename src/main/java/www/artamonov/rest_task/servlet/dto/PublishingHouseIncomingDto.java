package www.artamonov.rest_task.servlet.dto;

public class PublishingHouseIncomingDto {
    private Long id;
    private String name;

    public PublishingHouseIncomingDto() {
    }

    public PublishingHouseIncomingDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
