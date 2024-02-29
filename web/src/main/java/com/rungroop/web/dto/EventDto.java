package com.rungroop.web.dto;

import com.rungroop.web.models.Club;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {
    private long id;
    @NotEmpty(message = "Please give a name!")
    private String name;
    @NotEmpty(message = "Please give start time!")
    private LocalDateTime startTime;
    @NotEmpty(message = "Please give end time!")
    private LocalDateTime endTIme;
    @NotEmpty(message = "Please give a type!")
    private String type;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    @NotEmpty(message = "")
    private Club club;
}
