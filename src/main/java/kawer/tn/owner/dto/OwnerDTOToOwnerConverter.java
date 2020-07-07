package kawer.tn.owner.dto;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import kawer.tn.owner.Owner;
import kawer.tn.owner.dto.OwnerDTO;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class OwnerDTOToOwnerConverter implements Converter<OwnerDTO, Owner> {
    @Override
    @Synchronized
    @Nullable
    public Owner convert(OwnerDTO ownerDTO) {
        if (ownerDTO == null)
            return null;
        final Owner owner = new Owner();
        owner.setId(ownerDTO.getId());
        owner.setEmail(ownerDTO.getEmail());
        owner.setFirstName(ownerDTO.getFirstName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setUsername(ownerDTO.getUsername());
        owner.setPassword(ownerDTO.getPassword());
        owner.setNumber(ownerDTO.getNumber());
        return owner;
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
