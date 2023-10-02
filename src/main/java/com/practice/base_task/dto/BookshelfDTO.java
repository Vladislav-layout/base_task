package com.practice.base_task.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Сущность шкафа")
@Data
public class BookshelfDTO {
    @Schema(description = "id шкафа")
    private Long shelfId;
    @Schema(description = "Имя шкафа")
    private String shelfName;
    @Schema(description = "Локация шкафа")
    private String location;
}
