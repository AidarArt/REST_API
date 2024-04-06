package www.artamonov.rest_task.servlet.mapper.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import www.artamonov.rest_task.model.AuthorEntity;
import www.artamonov.rest_task.servlet.dto.AuthorIncomingDto;
import www.artamonov.rest_task.servlet.dto.AuthorOutGoingDto;
import www.artamonov.rest_task.servlet.mapper.AuthorDtoMapper;

class AuthorDtoMapperImplTest {
    private final AuthorDtoMapper dtoMapper = new AuthorDtoMapperImpl();
    private AuthorEntity authorEntity;
    private AuthorIncomingDto incomingDto;
    private AuthorOutGoingDto outGoingDto;

    @BeforeEach
    void setUp() {
        authorEntity = new AuthorEntity();
        authorEntity.setId(1L);
        authorEntity.setName("name");
        authorEntity.setSurname("surname");

        incomingDto = new AuthorIncomingDto();
        incomingDto.setName("name");
        incomingDto.setSurname("surname");

        outGoingDto = new AuthorOutGoingDto();
        outGoingDto.setId(1L);
        outGoingDto.setName("name");
        outGoingDto.setSurname("surname");
    }

    @Test
    void mapToEntity() {
        Assertions.assertEquals(authorEntity.getName(), dtoMapper.map(incomingDto).getName());
        Assertions.assertEquals(authorEntity.getSurname(), dtoMapper.map(incomingDto).getSurname());
    }

    @Test
    void mapToOutGoingDto() {
        Assertions.assertEquals(outGoingDto, dtoMapper.map(authorEntity));
    }
}
