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

    public BookmarkGroupDTO(String name, int displayOrder) {
        this.name = name;
        this.displayOrder = displayOrder;
    }
}
