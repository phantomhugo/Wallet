package com.hugo.wallet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 *
 * @author hugo
 */
@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String routingNumber;
    @Column
    private String nationalIdentificationNumber;
    @Column
    private String accountNumber;
    @Column
    private String bankName;
    @Column
    private String currency;
}
