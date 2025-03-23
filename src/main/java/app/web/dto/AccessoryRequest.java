package app.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class AccessoryRequest {
    @NotBlank
    private String accessoryName;
    @NotBlank
    private String description;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private String imageUrl;
}
