package kawer.tn.owner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerService ownerService;

    Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder()
                .id(1L)
                .build();
    }

    @Test
    void saveOwner() {
        when(ownerRepository.save(any())).thenReturn(owner);
        Owner savedOwner = ownerService.saveOwner(owner);
        assertNotNull(savedOwner);
        assertEquals(1L,savedOwner.getId());

    }
}