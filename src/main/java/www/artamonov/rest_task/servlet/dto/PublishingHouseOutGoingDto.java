package www.artamonov.rest_task.servlet.dto;

import java.util.Objects;

public class PublishingHouseOutGoingDto {
    private Long id;
    private String name;

    public PublishingHouseOutGoingDto() {
    }

    public PublishingHouseOutGoingDto(Long id, String name) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouseOutGoingDto dto = (PublishingHouseOutGoingDto) o;
        return Objects.equals(id, dto.id) && Objects.equals(name, dto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
