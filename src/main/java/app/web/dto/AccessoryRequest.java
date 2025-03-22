package app.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccessoryRequest {
    private String accessoryName;
    private String description;
    private BigDecimal price;
    private String imageUrl;
}
