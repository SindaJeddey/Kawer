package kawer.tn.field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FieldServiceTest {

    @Mock
    FieldRepository fieldRepository;

    @InjectMocks
    FieldService fieldService;

    Field field;

    @BeforeEach
    void setUp() {
        field = Field.builder()
                .id(1L)
                .capacity(Capacities.LOW.getCapacity())
                .name("Manzah")
                .build();
    }

    @Test
    void saveField() {
        when(fieldRepository.save(any())).thenReturn(field);
        Field savedField = fieldService.saveField(field);
        assertNotNull(savedField);
        assertEquals(1L, savedField.getId());
    }
}