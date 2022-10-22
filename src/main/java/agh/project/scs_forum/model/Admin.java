package agh.project.scs_forum.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;
    private String userId;
    private String addedBy;

}

