package org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;


@AllArgsConstructor
@Data
public class LocationHistoryDTO {
    private int id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Timestamp timestamp;
}
