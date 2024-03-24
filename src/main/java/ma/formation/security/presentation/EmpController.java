package ma.formation.security.presentation;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.formation.security.domaine.EmpVo;
import ma.formation.security.service.IEmpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class EmpController {
    private IEmpService empService;
    @GetMapping("/employees")
    public ResponseEntity<List<EmpVo>> getAll() {
        return new ResponseEntity<>(empService.getEmployees(),HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmpVo> getEmpById(@PathVariable(name="id") Long id) {
     return new ResponseEntity<>(empService.getEmpById(id), HttpStatus.OK);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<String> createEmp(@RequestBody @Valid  EmpVo vo) {
       empService.save(vo);
       return new ResponseEntity<>("Emp is created with success", HttpStatus.CREATED);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<String> updateEmp(@PathVariable(name="id") Long id,@RequestBody @Valid EmpVo vo) {
       EmpVo empFound=empService.getEmpById(id);
       vo.setId(id);
       empService.save(vo);
        return new ResponseEntity<>("Emp is updated with success", HttpStatus.OK);
    }
    @DeleteMapping("/admin/delete/{id}")
    ResponseEntity<String> deleteEmp(@PathVariable(name="id") Long id) {
        empService.delete(id);
        return new ResponseEntity<>("Emp is removed with success", HttpStatus.OK);
    }

    @GetMapping("/employees/{index}/{size}")
    public ResponseEntity<List<EmpVo>> getEmployeesByInterval(@PathVariable(name="index") int index,@PathVariable(name="size") int size) {
        return new ResponseEntity<>(empService.findAll(index,size), HttpStatus.OK);
    }

    @GetMapping("/employees/sort/{fieldname}")
    public ResponseEntity<List<EmpVo>> getEmployeesWithSort(@PathVariable(name="fieldname") String fieldname) {
        return new ResponseEntity<>(empService.sortBy(fieldname), HttpStatus.OK);
    }

    @GetMapping("/employees/paginator")
    public ResponseEntity<Map<String,Object>> getEmployeesByIntervalles(@RequestParam Map<String,String> parameters) {
        Integer size=Integer.parseInt(parameters.get("size"));
        Integer page=Integer.parseInt(parameters.get("page"));
        List<EmpVo> employees=empService.findAll(page,size);
        Integer employeesNumber=empService.getEmployees().size();

        Map<String,Object> result=new HashMap<>();
        result.put("employees",employees);
        result.put("size",employeesNumber);

        return new ResponseEntity<>(result,HttpStatus.OK);

    }

}
