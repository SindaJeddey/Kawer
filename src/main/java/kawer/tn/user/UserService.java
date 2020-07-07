package kawer.tn.user;


import kawer.tn.security.auth.ApplicationUserRepository;
import kawer.tn.user.dto.UserDTO;
import kawer.tn.user.dto.UserDTOToUserConverter;
import kawer.tn.user.dto.UserToUserDTOConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserToUserDTOConverter toUserDTOConverter;
    private final UserDTOToUserConverter dtoToUserConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserRepository applicationUserRepository;

    public UserService(UserToUserDTOConverter toUserDTOConverter,
                       UserDTOToUserConverter dtoToUserConverter,
                       UserRepository userRepository, PasswordEncoder passwordEncoder,
                       ApplicationUserRepository applicationUserRepository) {
        this.toUserDTOConverter = toUserDTOConverter;
        this.dtoToUserConverter = dtoToUserConverter;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationUserRepository = applicationUserRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<UserDTO> getAllUsers(){
        List<UserDTO> dtos = userRepository.findAll()
                .stream()
                .map(user -> toUserDTOConverter.convert(user))
                .collect(Collectors.toList());
        return dtos;
    }

    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new NullPointerException("User "+id+" not found"));
        UserDTO convert = toUserDTOConverter.convert(user);
        return convert;
    }

    public UserDTO saveUser(UserDTO userDTO){
        User userToSave = dtoToUserConverter.convert(userDTO);
        User savedUser = userRepository.save(userToSave);
        UserDTO savedUserDto = toUserDTOConverter.convert(savedUser);
        applicationUserRepository.saveApplicationUser(savedUser.getUsername(),savedUser.getPassword(),"USER");
        return savedUserDto;
    }

    public UserDTO updateUser(UserDTO dto, Long userId) {
        User userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new NullPointerException("User "+userId+" not found"));
        User updates = dtoToUserConverter.convert(dto);
        if (updates.getFirstName() != null)
            userToUpdate.setFirstName(updates.getFirstName());
        if (updates.getLastName() != null)
            userToUpdate.setLastName(updates.getLastName());
        if (updates.getBirthday() != null)
            userToUpdate.setBirthday(updates.getBirthday());
        if (updates.getEmail() != null)
            userToUpdate.setEmail(updates.getEmail());
        if(updates.isSubscribedToNewsLetter() != userToUpdate.isSubscribedToNewsLetter())
            userToUpdate.setSubscribedToNewsLetter(updates.isSubscribedToNewsLetter());
        User savedUser = userRepository.save(userToUpdate);
        UserDTO savedUserDto = toUserDTOConverter.convert(savedUser);
        return savedUserDto;
    }

    public void deleteUser(Long id){
        String username = userRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("User "+id+" not found"))
                .getUsername();
        applicationUserRepository.deleteApplicationUser(username,"USER");
        userRepository.deleteById(id);
    }
}
