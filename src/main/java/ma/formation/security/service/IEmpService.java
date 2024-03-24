package ma.formation.security.service;

import ma.formation.security.domaine.EmpVo;

import java.util.List;
import java.util.Optional;

public interface IEmpService {
    List<EmpVo> getEmployees();
    void save(EmpVo emp);
    EmpVo getEmpById(Long id);
    void delete(Long id);
    List<EmpVo> findBySalary(Double salary);
    List<EmpVo> findByFonction(String designation);
    List<EmpVo> findBySalaryAndFonction(Double salary, String fonction);
    //Pour la pagination
    List<EmpVo> findAll(int pageId, int size);
    //pour le tri
    List<EmpVo> sortBy(String fieldName);
}

