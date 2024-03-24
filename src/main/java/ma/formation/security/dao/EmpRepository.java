package ma.formation.security.dao;

import ma.formation.security.service.model.Emp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp,Long> {
    List<Emp> findBySalary(Double salary);
    List<Emp> findByFonction(String designation);
    List<Emp> findBySalaryAndFonction(Double salary, String fonction);

}
