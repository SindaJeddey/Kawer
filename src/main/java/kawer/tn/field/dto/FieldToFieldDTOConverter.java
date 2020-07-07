package kawer.tn.field.dto;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import kawer.tn.field.Field;
import kawer.tn.field.dto.FieldDTO;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class FieldToFieldDTOConverter implements Converter<Field, FieldDTO> {
    @Override
    @Synchronized
    @Nullable
    public FieldDTO convert(Field field) {
        if (field == null)
            return null;
        final FieldDTO dto = new FieldDTO();
        dto.setId(field.getId());
        dto.setCapacity(field.getCapacity());
        dto.setContactNumber(dto.getContactNumber());
        dto.setDescription(field.getDescription());
        dto.setLocation(field.getLocation());
        dto.setName(field.getName());
        dto.setPrice(field.getPrice());
        dto.setAmenities(field.getAmenities());
        return dto;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
