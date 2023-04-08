package ma.emsi.ap_jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
//ajouter les getters et setters
@Data
//Constructeur sans param
@NoArgsConstructor
//Constructeur avec param
@AllArgsConstructor
public class Patient implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;
    private int score;




}