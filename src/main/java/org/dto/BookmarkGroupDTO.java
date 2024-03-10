package org.dto;

import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;

@Data
@Getter

public class BookmarkGroupDTO {
    private int id;
    private String name;
    private int displayOrder;
    private Timestamp addedTime;
    private Timestamp editedTime;
    private String remarks;

    public BookmarkGroupDTO(String name, int displayOrder, String remarks) {
        this.name = name;
        this.displayOrder = displayOrder;
        this.remarks = remarks;
    }
}
