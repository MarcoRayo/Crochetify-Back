package utez.edu.mx.crochetifyBack.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name = "name", length = 300) 
    private String name;

    @Column(name = "email", length = 300) 
    private String email;

    @Column(name = "password", length = 300) 
    private String password;

    @Column(name = "status")
    private boolean status;

    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String image;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.PERSIST)
    private Role role;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore 
    private Cart cart; 
}
