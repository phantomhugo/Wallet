package com.hugo.wallet.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import lombok.Data;

/**
 *
 * @author hugo
 */
@Entity
@Data
public class Transfer implements Serializable {

    public enum Status {
        SUCEED(0),
        FAILED(1),
        IN_PROGRESS(2);

        private final int value;

        Status(final int newValue) {
            value = newValue;
        }

        public int getStatus() {
            return value;
        }
    }
    @Id
    UUID transactionId;

    @Column
    Double amount;

    @Column
    Integer status;

    @Column
    Date transactionDate;

    @Column
    @JsonProperty("user_id")
    Integer userId;
}
