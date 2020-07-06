package kawer.tn.field;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import jdk.internal.jline.internal.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class FieldTFieldDTOConverter implements Converter<Field,FieldDTO> {
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
        return null;
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
