package kawer.tn.user;

import kawer.tn.user.dto.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public UserDTO getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping("/new")
    @PreAuthorize("permitAll()")
    public UserDTO addNewUser(@RequestBody UserDTO dto){
        return userService.saveUser(dto);
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAnyRole('USER')")
    public UserDTO modifyUser(@RequestBody UserDTO dto, @PathVariable Long userId){
        UserDTO updatedDto = userService.updateUser(dto,userId);
        return updatedDto;
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
