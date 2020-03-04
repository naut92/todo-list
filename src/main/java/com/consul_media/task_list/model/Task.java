package com.consul_media.task_list.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @NonNull
    Long id;
    String heading;
    String text;
    String date_added;
    String edit_date;
    String status;
}
