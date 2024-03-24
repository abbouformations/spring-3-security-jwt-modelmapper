package ma.formation.security.domaine;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleVo implements GrantedAuthority {
    private String authority;
}
