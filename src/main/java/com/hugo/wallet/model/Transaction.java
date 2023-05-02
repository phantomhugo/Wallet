/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hugo.wallet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author hugo
 */
@Entity
@Data
public class Transaction implements Serializable {

    @Id
    @JsonProperty("wallet_transaction_id")
    private Integer walletTransactionId;

    @Column
    private Double amount;

    @Column
    private Integer user_id;
}
