package ma.formation.security.dao;

import ma.formation.security.service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByAuthority(String authority);
}
