package io.jmerta.tau.domain.accountManagment.util;

import io.jmerta.tau.TauApplication;
import io.jmerta.tau.config.DataConfig;
import io.jmerta.tau.domain.accountManagment.entity.Account;
import io.jmerta.tau.domain.accountManagment.service.ManageAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@MybatisTest(excludeAutoConfiguration = {AutoConfigureTestDatabase.class, SpringBootTest.class})
@SpringBootTest(classes = {TauApplication.class, DataConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(ManageAccount.class)
public class AuthManagerTest {

    @Mock
    ManageAccount manageAccount;

    @Mock
    Account account;

    @InjectMocks
    AuthManager authManager;

    @Test(expected = UsernameNotFoundException.class)
    public void testAuthenticateMethodForNullUserValue() {
        String token = "exToken";
        when(manageAccount.loadUserByToken(token)).thenReturn(null);

        authManager.authenticate(token);
    }

    @Test(expected = SessionAuthenticationException.class)
    public void testAuthenticateMethodForExpiredCredentials() {
        String token = "exToken";

        Account account2 = new Account("user", null,null,null);
        when(account.isCredentialsNonExpired()).thenReturn(true);
        when(manageAccount.loadUserByToken(token)).thenReturn(account2);

        authManager.authenticate(token);
    }

}
