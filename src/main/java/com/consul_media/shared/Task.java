package com.consul_media.shared;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task {
    private Long id;
    private String heading;
    private String text;
    private String date_added;
    private String edit_date;
    private String status;

    public String getText() {
        return text;
    }
}
