package io.asmame.tau.domain.taskManagment.entity;

import lombok.Data;

@Data
public class Task {

    private long id;
    private boolean isDone;
    private String name;
    private String content;
    private String comment;
    private long categoryId;
    private long acountId;
}
