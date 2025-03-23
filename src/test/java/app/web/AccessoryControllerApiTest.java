package app.web;

import app.model.Accessory;
import app.service.AccessoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccessoryController.class)
public class AccessoryControllerApiTest {

    @MockitoBean
    private AccessoryService accessoryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllAccessories_returnsAccessoryList() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Accessory accessory1 = Accessory.builder()
                .id(id1)
                .accessoryName("Toy Car Wheel")
                .description("Replacement wheel for toy cars")
                .price(BigDecimal.valueOf(4.99))
                .imageUrl("http://example.com/toy-car-wheel.jpg")
                .build();

        Accessory accessory2 = Accessory.builder()
                .id(id2)
                .accessoryName("Toy Car Door")
                .description("Replacement door for toy cars")
                .price(BigDecimal.valueOf(6.99))
                .imageUrl("http://example.com/toy-car-door.jpg")
                .build();

        List<Accessory> mockAccessories = Arrays.asList(accessory1, accessory2);

        when(accessoryService.getAllAccessories()).thenReturn(mockAccessories);

        mockMvc.perform(get("/api/accessories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(id1.toString()))
                .andExpect(jsonPath("$[0].accessoryName").value("Toy Car Wheel"))
                .andExpect(jsonPath("$[0].price").value(4.99))
                .andExpect(jsonPath("$[1].id").value(id2.toString()))
                .andExpect(jsonPath("$[1].accessoryName").value("Toy Car Door"))
                .andExpect(jsonPath("$[1].price").value(6.99));
    }

    @Test
    void getAllAccessories_returnsListOfAccessories() throws Exception {
        Accessory accessory1 = Accessory.builder()
                .id(UUID.randomUUID())
                .accessoryName("Toy Car Wheel")
                .description("Replacement wheel")
                .price(BigDecimal.valueOf(4.99))
                .imageUrl("http://example.com/toy-car-wheel.jpg")
                .build();

        Accessory accessory2 = Accessory.builder()
                .id(UUID.randomUUID())
                .accessoryName("Toy Car Battery")
                .description("Replacement battery")
                .price(BigDecimal.valueOf(9.99))
                .imageUrl("http://example.com/toy-car-battery.jpg")
                .build();

        List<Accessory> mockAccessories = Arrays.asList(accessory1, accessory2);

        when(accessoryService.getAllAccessories()).thenReturn(mockAccessories);

        MockHttpServletRequestBuilder request = get("/api/accessories");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(accessory1.getId().toString()))
                .andExpect(jsonPath("$[0].accessoryName").value("Toy Car Wheel"))
                .andExpect(jsonPath("$[1].id").value(accessory2.getId().toString()))
                .andExpect(jsonPath("$[1].accessoryName").value("Toy Car Battery"));
    }
}
