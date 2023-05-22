package pl.zajavka.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UsersRegistryServiceTest {

    @InjectMocks
    private UsersRegistryService usersRegistryService;

    @Mock
    private UsersRepository usersRepository;

    @Test
    @DisplayName("Adding users to the registry works successfully")
    void test1() {
        //given
        final var user = User.builder()
                .name("Roman")
                .surname("Adamski")
                .phone("55545")
                .build();
        BDDMockito.given(usersRepository.contains(user.getPhone())).willReturn(false);

        //when
        usersRegistryService.register(user);

        //then
        BDDMockito.then(usersRepository)
                .should()
                .put(user);
    }

    @Test
    @DisplayName("Adding users with the same phone number twice fails ")
    void test2() {
        //given
        final var user = User.builder()
                .name("Roman")
                .surname("Adamski")
                .phone("55545")
                .build();
        BDDMockito.given(usersRepository.contains(user.getPhone())).willReturn(true);

        //when
        try{
            usersRegistryService.register(user);
            Assertions.fail("Should throw exception");
        } catch (RuntimeException ignore) {}

        //then
        BDDMockito.then(usersRepository)
                .should(Mockito.never())
                .put(user);

    }

    @Test
    @DisplayName("Adding users with too long phone number fails ")
    void test3() {
        //given
        final var user = User.builder()
                .name("Roman")
                .surname("Adamski")
                .phone("555498797885")
                .build();
        BDDMockito.given(usersRepository.contains(user.getPhone())).willReturn(false);
        BDDMockito.willThrow(new RuntimeException("Phone number is too long"))
                .given(usersRepository)
                .put(user);

        //when
        try{
            usersRegistryService.register(user);
            Assertions.fail("Should throw exception");
        } catch (RuntimeException ignore) {}

        //then
        BDDMockito.then(usersRepository)
                .should()
                .put(user);

    }


}