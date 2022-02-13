package mx.com.gm.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="usuario")
public class Usuario implements Serializable{
        private static final long serialVersionUID = 1L;
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long idUsuario;
        
        @NotEmpty
        @Column(name="username")
        private String userName;
        
        @NotEmpty
        private String password;
        
        // Establecer relacion uno - muchos
        @OneToMany
        @JoinColumn(name="id_usuario")
        private List<Rol> roles;
}
