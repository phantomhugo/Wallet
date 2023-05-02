package com.hugo.wallet.model;

import java.util.UUID;
import lombok.Data;

/**
 *
 * @author hugo
 */
@Data
public class PaymentInfo {

    Double amount;
    UUID id;
}
