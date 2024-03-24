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
public class User implements Serializable {
   @Id
   @GeneratedValue
   private Long id;
   @ManyToMany
   private List<Role> authorities=new ArrayList<>();
   private String password;
   @Column(unique = true)
   private String username;
   private boolean accountNonExpired;
   private boolean accountNonLocked;
   private boolean credentialsNonExpired;
   private boolean enabled;
}
