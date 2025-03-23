package app.dto;

import app.web.dto.AccessoryRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class AccessoryRequestUTest {

    @Test
    void buildToyCarAccessoryRequest_shouldSetAllFieldsCorrectly() {
        String accessoryName = "Toy Car Wheel";
        String description = "Replacement wheel for toy cars";
        BigDecimal price = BigDecimal.valueOf(4.99);
        String imageUrl = "http://example.com/toy-car-wheel.jpg";

        AccessoryRequest request = AccessoryRequest.builder()
                .accessoryName(accessoryName)
                .description(description)
                .price(price)
                .imageUrl(imageUrl)
                .build();

        assertEquals(accessoryName, request.getAccessoryName());
        assertEquals(description, request.getDescription());
        assertEquals(price, request.getPrice());
        assertEquals(imageUrl, request.getImageUrl());
    }

    @Test
    void createToyCarAccessoryRequest_withNoArgsConstructor_shouldHaveNullFields() {
        AccessoryRequest request = AccessoryRequest.builder().build();

        assertNull(request.getAccessoryName());
        assertNull(request.getDescription());
        assertNull(request.getPrice());
        assertNull(request.getImageUrl());
    }

    @Test
    void createToyCarAccessoryRequest_withAllArgsConstructor_shouldSetAllFields() {
        String accessoryName = "Toy Car Wheel";
        String description = "Replacement wheel for toy cars";
        BigDecimal price = BigDecimal.valueOf(4.99);
        String imageUrl = "http://example.com/toy-car-wheel.jpg";

        AccessoryRequest request = new AccessoryRequest(accessoryName, description, price, imageUrl);

        assertEquals(accessoryName, request.getAccessoryName());
        assertEquals(description, request.getDescription());
        assertEquals(price, request.getPrice());
        assertEquals(imageUrl, request.getImageUrl());
    }

    @Test
    void setFields_usingSetterMethods_shouldSetFieldsCorrectly() {
        AccessoryRequest request = new AccessoryRequest(null, null, null, null);

        String accessoryName = "Toy Car Wheel";
        String description = "Replacement wheel for toy cars";
        BigDecimal price = BigDecimal.valueOf(4.99);
        String imageUrl = "http://example.com/toy-car-wheel.jpg";

        request.setAccessoryName(accessoryName);
        request.setDescription(description);
        request.setPrice(price);
        request.setImageUrl(imageUrl);

        assertEquals(accessoryName, request.getAccessoryName());
        assertEquals(description, request.getDescription());
        assertEquals(price, request.getPrice());
        assertEquals(imageUrl, request.getImageUrl());
    }

    @Test
    void getFields_usingGetterMethods_shouldRetrieveFieldsCorrectly() {
        String accessoryName = "Toy Car Wheel";
        String description = "Replacement wheel for toy cars";
        BigDecimal price = BigDecimal.valueOf(4.99);
        String imageUrl = "http://example.com/toy-car-wheel.jpg";

        AccessoryRequest request = AccessoryRequest.builder()
                .accessoryName(accessoryName)
                .description(description)
                .price(price)
                .imageUrl(imageUrl)
                .build();

        assertEquals(accessoryName, request.getAccessoryName());
        assertEquals(description, request.getDescription());
        assertEquals(price, request.getPrice());
        assertEquals(imageUrl, request.getImageUrl());
    }
}
