package kawer.tn.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

public enum ApplicationRoles {
    USER(Sets.newHashSet(new SimpleGrantedAuthority("ROLE_USER"))),
    OWNER(Sets.newHashSet(new SimpleGrantedAuthority("ROLE_OWNER"))),
    ADMIN(Sets.newHashSet((new SimpleGrantedAuthority("ROLE_ADMIN"))));

    private final Set<SimpleGrantedAuthority> role ;
    ApplicationRoles(Set<SimpleGrantedAuthority> role) {
        this.role = role;
    }

    public Set<SimpleGrantedAuthority> getRole() {
        return role;
    }
}
