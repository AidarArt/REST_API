package www.artamonov.rest_task.servlet;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import www.artamonov.rest_task.service.mapper.PublishingHouseService;

@ExtendWith(MockitoExtension.class)
class PublishingHoseServletTest {

    @Mock
    private PublishingHouseService publishingHouseService;

    @InjectMocks
    private PublishingHouseServlet publishingHouseServlet;


}
