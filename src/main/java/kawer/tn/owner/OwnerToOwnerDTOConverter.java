package kawer.tn.owner;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import jdk.internal.jline.internal.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class OwnerToOwnerDTOConverter implements Converter<Owner,OwnerDTO> {
    @Override
    @Synchronized
    @Nullable
    public OwnerDTO convert(Owner owner) {
        if (owner == null)
            return null;
        final OwnerDTO dto = new OwnerDTO();
        dto.setId(owner.getId());
        dto.setEmail(owner.getEmail());
        dto.setFirstName(owner.getFirstName());
        dto.setLastName(owner.getLastName());
        dto.setUsername(owner.getUsername());
        dto.setNumber(owner.getNumber());
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
