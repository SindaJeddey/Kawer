package kawer.tn.security.auth;

import kawer.tn.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRepository {
    public Optional<ApplicationUser> saveApplicationUser(String username, String password, String role);
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username);
    public void deleteApplicationUser(String username,String role);
}
