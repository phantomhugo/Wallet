package com.hugo.wallet.model;

import lombok.Data;

/**
 *
 * @author hugo
 */
@Data
public class Destination {

    String name;
    private Account account;
}
