package sg.edu.iss.team6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class User implements Serializable {

    @Id
    
    private String username;
    private String password;

    // role (lecturer, admin, student)?
}
