package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "BankAccountDetails")
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {

    @Id
    @GeneratedValue(generator = "bank_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "bank_seq",
            sequenceName = "BANK_ID_SEQ",
            allocationSize = 1
    )
    private Integer bid;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private Long mobileNo;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_active")
    private boolean isActive = true;

    // meta-data fields
    
    @Column(unique = true)
    private Long accountNo;
    
    @PrePersist
    public void generatedValue() {
    	if(accountNo == null) {
    		this.accountNo = 100000000000L + (long)(Math.random()*900000000000L);
    	}
    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;
}
