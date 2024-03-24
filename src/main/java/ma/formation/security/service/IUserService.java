package ma.formation.security.service;

import ma.formation.security.domaine.RoleVo;
import ma.formation.security.domaine.UserVo;

public interface IUserService {
    UserVo save(UserVo userVo);
    RoleVo save(RoleVo roleVo);
}
