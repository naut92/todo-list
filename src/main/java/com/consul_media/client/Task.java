package com.consul_media.client;

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
}
