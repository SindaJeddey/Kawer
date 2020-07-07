package kawer.tn.security.auth;

import kawer.tn.security.ApplicationRoles;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository{

    private final List<ApplicationUser> users;

    public ApplicationUserRepositoryImpl(PasswordEncoder passwordEncoder) {
        this.users =Arrays.asList(
                new ApplicationUser("sady",
                        passwordEncoder.encode("password"),
                        ApplicationRoles.ADMIN.getRole(),
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser("douda",
                        passwordEncoder.encode("password"),
                        ApplicationRoles.USER.getRole(),
                        true,
                        true,
                        true,
                        true),

                new ApplicationUser("bada",
                        passwordEncoder.encode("password"),
                        ApplicationRoles.OWNER.getRole(),
                        true,
                        true,
                        true,
                        true));
    }

    @Override
    public Optional<ApplicationUser> saveApplicationUser(String username, String password, String role) {
        Set<SimpleGrantedAuthority> auth = new HashSet<>();
        if (role.equals("ADMIN"))
             auth = ApplicationRoles.ADMIN.getRole();
        else if (role.equals("OWNER"))
            auth = ApplicationRoles.OWNER.getRole();
        else if (role.equals("USER"))
            auth = ApplicationRoles.USER.getRole();

        ApplicationUser user = new ApplicationUser(username,
                password,
                auth,
                true,
                true,
                true,
                true
                );
        return Optional.of(user);
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        ApplicationUser user = users.stream()
                .filter(applicationUser -> applicationUser.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Username "+username+" not found"));
        return Optional.of(user);
    }


    @Override
    public void deleteApplicationUser(String username,String role) {
        ApplicationUser toDelete = users.stream()
                .filter(applicationUser -> applicationUser.getAuthorities().iterator().next().equals(role))
                .filter(applicationUser -> applicationUser.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Username "+username+" not found"));
        users.remove(toDelete);
    }


}
