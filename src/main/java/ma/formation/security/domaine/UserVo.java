package ma.formation.security.domaine;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVo implements UserDetails {
    private Long id;
    private List<RoleVo> authorities;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}
