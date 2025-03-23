package app.service;


import app.model.Accessory;
import app.repository.AccessoryRepository;
import app.web.dto.AccessoryRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccessoryServiceTest {

    @Mock
    private AccessoryRepository accessoryRepository;

    @InjectMocks
    private AccessoryService accessoryService;

    @Test
    void getAllAccessories_shouldReturnListOfAccessories() {
        Accessory accessory1 = Accessory.builder()
                .id(UUID.randomUUID())
                .accessoryName("Accessory 1")
                .description("Description 1")
                .price(new BigDecimal("10.00"))
                .imageUrl("image1.jpg")
                .build();

        Accessory accessory2 = Accessory.builder()
                .id(UUID.randomUUID())
                .accessoryName("Accessory 2")
                .description("Description 2")
                .price(new BigDecimal("20.00"))
                .imageUrl("image2.jpg")
                .build();

        List<Accessory> accessories = Arrays.asList(accessory1, accessory2);

        when(accessoryRepository.findAll()).thenReturn(accessories);

        List<Accessory> result = accessoryService.getAllAccessories();

        assertEquals(2, result.size());
        assertEquals(accessories, result);
    }

    @Test
    void getAllAccessories_shouldReturnEmptyList_whenNoAccessoriesExist() {
        when(accessoryRepository.findAll()).thenReturn(List.of());

        List<Accessory> result = accessoryService.getAllAccessories();

        assertEquals(0, result.size());
    }



    @Test
    void shouldCreateAccessorySuccessfully() {

        AccessoryRequest accessoryRequest = AccessoryRequest.builder()
                .accessoryName("Car Seat")
                .description("Comfortable leather car seat")
                .price(new BigDecimal("150.00"))
                .imageUrl("example.com/seat.jpg")
                .build();

        accessoryService.createAccessory(accessoryRequest);
        ArgumentCaptor<Accessory> accessoryCaptor = ArgumentCaptor.forClass(Accessory.class);
        verify(accessoryRepository, times(1)).save(accessoryCaptor.capture());
        Accessory savedAccessory = accessoryCaptor.getValue();
        assertNotNull(savedAccessory);
        assertEquals(accessoryRequest.getAccessoryName(), savedAccessory.getAccessoryName());
        assertEquals(accessoryRequest.getDescription(), savedAccessory.getDescription());
        assertEquals(accessoryRequest.getPrice(), savedAccessory.getPrice());
        assertEquals(accessoryRequest.getImageUrl(), savedAccessory.getImageUrl());
    }

    @Test
    void shouldCreateAccessorySuccessfullyWithValidData() {

        AccessoryRequest accessoryRequest = AccessoryRequest.builder()
                .accessoryName("Car Seat")
                .description("Comfortable leather car seat")
                .price(new BigDecimal("150.00"))
                .imageUrl("example.com/seat.jpg")
                .build();
        accessoryService.createAccessory(accessoryRequest);

        ArgumentCaptor<Accessory> accessoryCaptor = ArgumentCaptor.forClass(Accessory.class);
        verify(accessoryRepository, times(1)).save(accessoryCaptor.capture());
        Accessory savedAccessory = accessoryCaptor.getValue();
        assertNotNull(savedAccessory);
        assertEquals(accessoryRequest.getAccessoryName(), savedAccessory.getAccessoryName());
        assertEquals(accessoryRequest.getDescription(), savedAccessory.getDescription());
        assertEquals(accessoryRequest.getPrice(), savedAccessory.getPrice());
        assertEquals(accessoryRequest.getImageUrl(), savedAccessory.getImageUrl());
    }

    @Test
    void shouldCreateAccessoryWithNullDescription() {

        AccessoryRequest accessoryRequest = AccessoryRequest.builder()
                .accessoryName("Car Seat")
                .description(null)  // Null description
                .price(new BigDecimal("150.00"))
                .imageUrl("example.com/seat.jpg")
                .build();

        accessoryService.createAccessory(accessoryRequest);

        ArgumentCaptor<Accessory> accessoryCaptor = ArgumentCaptor.forClass(Accessory.class);
        verify(accessoryRepository, times(1)).save(accessoryCaptor.capture());

        Accessory savedAccessory = accessoryCaptor.getValue();
        assertNotNull(savedAccessory);
        assertNull(savedAccessory.getDescription());
    }
    @Test
    void shouldCreateAccessoryWithNullImageUrl() {

        AccessoryRequest accessoryRequest = AccessoryRequest.builder()
                .accessoryName("Car Seat")
                .description("Car seat")
                .price(new BigDecimal("150.00"))
                .imageUrl(null)
                .build();

        accessoryService.createAccessory(accessoryRequest);

        ArgumentCaptor<Accessory> accessoryCaptor = ArgumentCaptor.forClass(Accessory.class);
        verify(accessoryRepository, times(1)).save(accessoryCaptor.capture());
        Accessory savedAccessory = accessoryCaptor.getValue();
        assertNotNull(savedAccessory);
        assertNull(savedAccessory.getImageUrl());
    }

    @Test
    void shouldReturnAccessoryWhenFound() {

        UUID id = UUID.randomUUID();
        Accessory expectedAccessory = Accessory.builder()
                .id(id)
                .accessoryName("Car Seat")
                .description("Car seat")
                .price(new BigDecimal("150.00"))
                .imageUrl("example.com/seat.jpg")
                .build();

        when(accessoryRepository.findById(id)).thenReturn(Optional.of(expectedAccessory));
        Accessory result = accessoryService.getAccessoryById(id);
        assertNotNull(result);
        assertEquals(expectedAccessory, result);
        verify(accessoryRepository, times(1)).findById(id);
    }

    @Test
    void shouldReturnNullWhenAccessoryNotFound() {
        UUID id = UUID.randomUUID();
        when(accessoryRepository.findById(id)).thenReturn(Optional.empty());
        Accessory result = accessoryService.getAccessoryById(id);
        assertNull(result);
        verify(accessoryRepository, times(1)).findById(id);
    }

    @Test
    void shouldDeleteAccessoryWhenFound() {
        UUID id = UUID.randomUUID();
        Accessory accessory = Accessory.builder()
                .id(id)
                .accessoryName("Car Seat")
                .description("Comfortable leather car seat")
                .price(new BigDecimal("150.00"))
                .imageUrl("example.com/seat.jpg")
                .build();

        when(accessoryRepository.findById(id)).thenReturn(Optional.of(accessory));
        accessoryService.deleteAccessory(id);
        verify(accessoryRepository, times(1)).delete(accessory);
        verify(accessoryRepository, times(1)).findById(id);
    }
}