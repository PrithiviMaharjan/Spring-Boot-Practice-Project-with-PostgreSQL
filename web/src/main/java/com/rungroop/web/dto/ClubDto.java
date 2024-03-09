package com.rungroop.web.dto;

import com.rungroop.web.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ClubDto {
    private Long id;
    @NotEmpty(message= "Club title should not be empty!")
    private String title;
    @NotEmpty(message= "You must add a photo url!")
    private String photoUrl;
    @NotEmpty(message = "Content should not be empty!")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private UserEntity createdBy;
    private List<EventDto> events;
}