package org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class WifiBookmarkDTO {
    private int id;
    private int bookmarkGroupId;
    private String wifiId;
    private String wifiName;
    private Timestamp addedTime;
    private String bookmarkGroupName;
}
