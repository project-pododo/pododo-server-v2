package com.pododoserver.todo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestDTO {

    private final Long id;
    private final String name;
    private final Integer age;
    private final String comment;
}
