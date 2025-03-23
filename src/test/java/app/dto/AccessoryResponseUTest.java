    package app.dto;

    import app.web.dto.AccessoryResponse;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.junit.jupiter.MockitoExtension;

    import java.math.BigDecimal;
    import java.util.UUID;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertNotNull;

    @ExtendWith(MockitoExtension.class)
    public class AccessoryResponseUTest {
        @Test
        void accessoryResponseBuilder_shouldCreateObjectWithCorrectValues() {
            UUID id = UUID.randomUUID();
            String accessoryName = "Test Accessory";
            String description = "Test Description";
            BigDecimal price = new BigDecimal("100.00");
            String imageUrl = "test.jpg";

            AccessoryResponse response = AccessoryResponse.builder()
                    .id(id)
                    .accessoryName(accessoryName)
                    .description(description)
                    .price(price)
                    .imageUrl(imageUrl)
                    .build();

            assertNotNull(response);
            assertEquals(id, response.getId());
            assertEquals(accessoryName, response.getAccessoryName());
            assertEquals(description, response.getDescription());
            assertEquals(price, response.getPrice());
            assertEquals(imageUrl, response.getImageUrl());
        }

        @Test
        void accessoryResponseBuilder_shouldCreateObjectWithPartialValues() {
            UUID id = UUID.randomUUID();
            String accessoryName = "Partial Accessory";

            AccessoryResponse response = AccessoryResponse.builder()
                    .id(id)
                    .accessoryName(accessoryName)
                    .build();

            assertNotNull(response);
            assertEquals(id, response.getId());
            assertEquals(accessoryName, response.getAccessoryName());
        }

        @Test
        void accessoryResponseBuilder_shouldCreateObjectWithNullValues() {
            AccessoryResponse response = AccessoryResponse.builder().build();

            assertNotNull(response);
            assertEquals(null, response.getId());
            assertEquals(null, response.getAccessoryName());
            assertEquals(null, response.getDescription());
            assertEquals(null, response.getPrice());
            assertEquals(null, response.getImageUrl());
        }
    }
