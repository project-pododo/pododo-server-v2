package com.pododoserver.todo.dto;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class TodoSearchDto {

    private final LocalDateTime start;
    private final LocalDateTime end;

    @Builder
    TodoSearchDto(LocalDate startDate,
                  LocalDate endDate) {
        this.start = ObjectUtils.isNotEmpty(startDate) ? startDate.atStartOfDay() : null;
        this.end = ObjectUtils.isNotEmpty(endDate) ? endDate.atTime(23, 59, 59, 999) : null;
    }

    public boolean isAllSearch() {
        return ObjectUtils.isEmpty(start) && ObjectUtils.isEmpty(end);
    }
}
