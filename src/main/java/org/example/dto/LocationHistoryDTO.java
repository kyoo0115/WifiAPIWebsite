package org.example.dto;

import lombok.Data;

import java.security.Timestamp;

@Data
public class LocationHistoryDTO {
    private int id;
    private double latitude;
    private double longitude;
    private Timestamp timestamp;
}
