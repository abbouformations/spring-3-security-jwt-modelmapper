package ma.formation.security.service;

import lombok.AllArgsConstructor;
import ma.formation.security.dao.RoleRepository;
import ma.formation.security.dao.UserRepository;
import ma.formation.security.domaine.RoleVo;
import ma.formation.security.domaine.UserVo;
import ma.formation.security.service.model.Role;
import ma.formation.security.service.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService,IUserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return modelMapper.map(userRepository.findUserByUsername(username), UserVo.class);
    }

    @Override
    public UserVo save(UserVo userVo) {
        User userBo=modelMapper.map(userVo, User.class);
        userBo.setPassword(passwordEncoder.encode(userVo.getPassword()));
        List<Role> attachedRoles = userVo.getAuthorities().
                stream().
                map(authority -> roleRepository.findByAuthority(authority.getAuthority())).
                        collect(Collectors.toList());
      userBo.setAuthorities(attachedRoles);
      return modelMapper.map(userRepository.save(userBo),UserVo.class);

    }

    @Override
    public RoleVo save(RoleVo roleVo) {
        return modelMapper.map(roleRepository.save(modelMapper.map(roleVo,Role.class)), RoleVo.class);
    }
}
