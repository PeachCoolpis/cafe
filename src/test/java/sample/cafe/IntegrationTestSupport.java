package sample.cafe;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import sample.cafe.api.client.MailSendClient;

@SpringBootTest
@ActiveProfiles("test")
public abstract class IntegrationTestSupport {
    
    @MockBean
    protected MailSendClient mailSendClient;
}
