package com.hugo.wallet.model;

import lombok.Data;

/**
 *
 * @author hugo
 */
@Data
public class PaymentRequest {

    Source source;
    Destination destination;
    Double amount;
}
