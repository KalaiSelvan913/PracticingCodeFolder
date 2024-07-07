package com.kalaiselvan.springbootsecurity.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Role_Master")
@Getter
@Setter
public class Role extends Audit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1)
    private Long id;
	
	@Column(unique = true, nullable = false)
	private String roleCode;

    private String name;

    
//    @PrePersist
//    public void prePersist() {
//        this.roleCode = "ROLE-" + String.format("%05d", this.id);
//    }

}
