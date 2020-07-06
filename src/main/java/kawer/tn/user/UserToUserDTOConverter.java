package kawer.tn.user;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import jdk.internal.jline.internal.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDTOConverter implements Converter<User,UserDTO> {
    @Override
    @Synchronized
    @Nullable
    public UserDTO convert(User user) {
        if (user == null)
            return null;
        final UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBirthday(user.getBirthday());
        dto.setSubscribedToNewsLetter(user.isSubscribedToNewsLetter());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
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
