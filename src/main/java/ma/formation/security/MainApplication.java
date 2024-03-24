package ma.formation.security;

import ma.formation.security.domaine.EmpVo;
import ma.formation.security.domaine.RoleVo;
import ma.formation.security.domaine.UserVo;
import ma.formation.security.service.IEmpService;
import ma.formation.security.service.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class MainApplication {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDataBase(IEmpService empService, IUserService userService) {
        return (arg) -> {
            empService.save(EmpVo.builder().
                    name("EMPLOYE_1").
                    salary(15000.0).
                    fonction("INGENIEUR").
                    build());

            empService.save(EmpVo.builder().
                    name("EMPLOYE_2").
                    salary(13000.0).
                    fonction("INGENIEUR").
                    build());

          empService.save(EmpVo.builder().
                    name("EMPLOYE_3").
                    salary(5000.0).
                    fonction("OPERATEUR").
                    build());

            empService.save(EmpVo.builder().
                    name("EMPLOYE_4").
                    salary(30000.0).
                    fonction("Responsable").
                    build());

            RoleVo roleAdmin= RoleVo.builder().authority("ADMIN").build();
            RoleVo roleClient= RoleVo.builder().authority("CLIENT").build();
            userService.save(roleAdmin);
            userService.save(roleClient);

            UserVo admin=UserVo.builder().
                    username("admin").
                    password("admin").
                    accountNonLocked(true).
                    accountNonExpired(true).
                    credentialsNonExpired(true).
                    enabled(true).
                    authorities(List.of(roleAdmin)).
                    build();

            UserVo client=UserVo.builder().
                    username("client").
                    password("client").
                    accountNonLocked(true).
                    accountNonExpired(true).
                    credentialsNonExpired(true).
                    enabled(true).
                    authorities(List.of(roleClient)).
                    build();

            userService.save(admin);
            userService.save(client);

        };
    }

}
