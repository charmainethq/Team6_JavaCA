package sg.edu.iss.team6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Admin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;


    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    private String firstName;
    private String lastName;
    private String email;
    private int contactNo;
    private String address;
}
