package ma.formation.security.domaine;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpVo {
    private Long id;
    @NotEmpty
    private String name;
    @Min(value=3000,message = "the salary must be greater than 3000")
    private Double salary;
    @NotEmpty
    private String fonction;
}
