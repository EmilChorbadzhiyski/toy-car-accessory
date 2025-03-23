package app.web.dto;


import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccessoryResponse {
    private UUID id;
    private String accessoryName;
    private String description;
    private BigDecimal price;
    private String imageUrl;
}
