package sample.cafe.api.service.mail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafe.api.client.MailSendClient;
import sample.cafe.domain.history.mail.MailSendHistory;
import sample.cafe.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {
    
    @Mock
    private MailSendClient mailSendClient;
    
    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;
    
    @InjectMocks
    private MailService mailService;
    
    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail(){
        //given
        // MailSendClient mailSendClient = mock(MailSendClient.class);
        // MailSendHistoryRepository mailSendHistoryRepository = mock(MailSendHistoryRepository.class);
        
        
        //when
        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);
        
        boolean result = mailService.sendMail(
                "",
                "",
                "",
                ""
        );
        //then
        assertThat(result).isTrue();
        verify(mailSendHistoryRepository,times(1)).save(any(MailSendHistory.class));
    
    }
    
}