package com.practice.base_task.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Сущность шкафа")
@Data
public class BookshelfDTO {
    @Schema(description = "id шкафа")
    private Long shelfId;
    @JsonIgnore
    @Schema(description = "Имя шкафа")
    private String shelfName;
    @JsonIgnore
    @Schema(description = "Локация шкафа")
    private String location;
}
