package pl.zajavka.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    }

    @Test
    @DisplayName("Adding users with the same phone number twice fails ")
    void test2() {

    }

    @Test
    @DisplayName("Adding users with too long phone number fails ")
    void test3() {

    }


}