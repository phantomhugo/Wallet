/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hugo.wallet.model;

import lombok.Data;

/**
 *
 * @author hugo
 */
@Data
public class PaymentResponse {

    RequestInfo requestInfo;
    PaymentInfo paymentInfo;
}
