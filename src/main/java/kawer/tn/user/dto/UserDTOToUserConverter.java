package kawer.tn.user.dto;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import kawer.tn.user.User;
import kawer.tn.user.dto.UserDTO;
import org.springframework.lang.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;

@Component
public class UserDTOToUserConverter implements Converter<UserDTO, User> {
    @Override
    @Synchronized
    @Nullable
    public User convert(UserDTO userDTO) {
        if (userDTO == null)
            return null;
        final User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthday(userDTO.getBirthday());
        user.setSubscribedToNewsLetter(userDTO.isSubscribedToNewsLetter());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        return user;
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
