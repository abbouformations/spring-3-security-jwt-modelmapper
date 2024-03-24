package ma.formation.security.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Role implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users=new ArrayList<>();
}
