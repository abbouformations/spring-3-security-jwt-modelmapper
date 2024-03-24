package ma.formation.security.service.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    private String message;
}
