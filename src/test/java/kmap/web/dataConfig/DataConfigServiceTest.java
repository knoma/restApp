package kmap.web.dataConfig;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DataConfigServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @InjectMocks
    private DataConfigService dataConfigService = new DataConfigService();
    @Mock
    private DataConfigReopistory repositoryMock;

    @Test
    public void shouldInitializeWithTwoDemoDataConfig() {
        // act
        dataConfigService.initialize();
        // assert
        verify(repositoryMock, times(2)).save(any(DataConfig.class));
    }

    @Ignore
    @Test
    public void shouldThrowExceptionWhenDataSourceNotFound() {
        // arrange
        thrown.expect(DataConfigFoundException.class);
        thrown.expectMessage("user not found");

        when(repositoryMock.findByName("user")).thenReturn(null);
        // act
        dataConfigService.loadByName("user");
    }
//
//	@Test
//	public void shouldReturnUserDetails() {
//		// arrange
//		Account demoUser = new Account("user@example.com", "demo", "ROLE_USER");
//		when(accountRepositoryMock.findByEmail("user@example.com")).thenReturn(demoUser);
//
//		// act
//		UserDetails userDetails = userService.loadUserByUsername("user@example.com");
//
//		// assert
//		assertThat(demoUser.getEmail()).isEqualTo(userDetails.getUsername());
//		assertThat(demoUser.getPassword()).isEqualTo(userDetails.getPassword());
//        assertThat(hasAuthority(userDetails, demoUser.getRole()));
//	}
//
//	private boolean hasAuthority(UserDetails userDetails, String role) {
//		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//		for(GrantedAuthority authority : authorities) {
//			if(authority.getAuthority().equals(role)) {
//				return true;
//			}
//		}
//		return false;
//	}
}
