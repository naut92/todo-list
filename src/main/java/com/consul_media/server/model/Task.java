package com.consul_media.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
public class Task {
    @NonNull
    private Long id;
    private String heading;
    private String text;
    private String date_added;
    private String edit_date;
    private String status;
}
