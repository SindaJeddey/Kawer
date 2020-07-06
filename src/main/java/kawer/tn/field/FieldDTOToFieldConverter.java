package kawer.tn.field;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import jdk.internal.jline.internal.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class FieldDTOToFieldConverter implements Converter<FieldDTO,Field> {
    @Override
    @Synchronized
    @Nullable
    public Field convert(FieldDTO fieldDTO) {
        if(fieldDTO == null)
            return null;
        final Field field = new Field();
        field.setId(fieldDTO.getId());
        field.setCapacity(fieldDTO.getCapacity());
        field.setContactNumber(field.getContactNumber());
        field.setDescription(fieldDTO.getDescription());
        field.setLocation(fieldDTO.getLocation());
        field.setName(fieldDTO.getName());
        field.setPrice(fieldDTO.getPrice());
        return field;
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
