package ma.formation.security.presentation;

import lombok.AllArgsConstructor;
import ma.formation.security.domaine.RoleVo;
import ma.formation.security.domaine.TokenDto;
import ma.formation.security.domaine.UserRequest;
import ma.formation.security.jwt.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/auth")
@AllArgsConstructor
@RestController
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<TokenDto> login(@RequestBody UserRequest userRequest) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(userRequest.username(), userRequest.password()));
        String jwtToken=jwtUtils.generateJwtToken(authentication);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return new ResponseEntity<>(TokenDto.builder().jwtToken(jwtToken).username(userRequest.username()).
                roles(authorities.stream().
                        map(authority->authority.getAuthority()).
                        collect(Collectors.toList())).build(),
                HttpStatus.OK);
    }
}
