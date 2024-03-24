package ma.formation.security.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.formation.security.dao.EmpRepository;
import ma.formation.security.domaine.EmpVo;
import ma.formation.security.service.exception.BusinessException;
import ma.formation.security.service.model.Emp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AOP : Aspect Oriented Programming : le code qui traite la transaction est externalisé.
 * c'est l'itercépteur fourni par Spring qui va traiter la transaction
 *
 * ==> Séparer le traitement technique de la couche métier.
 */
@Transactional
@Service
@AllArgsConstructor
public class EmpServiceImpl implements IEmpService{
    private EmpRepository empRepository;
    private ModelMapper modelMapper;

    @Override
    public List<EmpVo> getEmployees() {
        return empRepository.findAll().stream().
                map(bo->modelMapper.map(bo,EmpVo.class)).
                collect(Collectors.toList());
    }

    @Override
    public void save(EmpVo dto) {
        empRepository.save(modelMapper.map(dto, Emp.class));
    }

    @Override
    public EmpVo getEmpById(Long id) {
        return modelMapper.map(empRepository.findById(id).
                orElseThrow(() -> new BusinessException(String.format("No Emp found with the id: %s", id))),EmpVo.class);
    }

    @Override
    public void delete(Long id) {
     empRepository.delete(empRepository.findById(id).
             orElseThrow(() -> new BusinessException(String.format("No Emp found with the id: %s", id))));
    }

    @Override
    public List<EmpVo> findBySalary(Double salary) {
        return empRepository.
                findBySalary(salary).stream().
                map(bo->modelMapper.map(bo,EmpVo.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<EmpVo> findByFonction(String fonction) {
        return empRepository.
                findByFonction(fonction).stream().
                map(bo->modelMapper.map(bo,EmpVo.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<EmpVo> findBySalaryAndFonction(Double salary, String fonction) {
        return empRepository.
                findBySalaryAndFonction(salary,fonction).stream().
                map(bo->modelMapper.map(bo,EmpVo.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<EmpVo> findAll(int pageId, int size) {
        return empRepository.
                findAll(PageRequest.of(pageId,size, Sort.Direction.ASC,"salary")).
                stream().map(bo->modelMapper.map(bo,EmpVo.class)).
                collect(Collectors.toList());
    }

    @Override
    public List<EmpVo> sortBy(String fieldName) {
        return empRepository.
                findAll(Sort.by(fieldName)).
                stream().map(bo->modelMapper.map(bo,EmpVo.class)).
                collect(Collectors.toList());
    }
}
