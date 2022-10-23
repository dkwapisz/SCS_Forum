package agh.project.scs_forum.model;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private String tempNewPassword;


    @Override
    public String toString() {
        return "UserId: " + userId + ", Username: " + username;
    }
}
