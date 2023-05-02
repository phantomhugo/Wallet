package com.hugo.wallet.model;

import lombok.Data;

/**
 *
 * @author hugo
 */
@Data
public class Source {

    String type;
    SourceInformation sourceInformation;
    Account account;
}
