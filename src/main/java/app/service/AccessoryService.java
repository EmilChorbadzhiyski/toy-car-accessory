package app.service;

import app.model.Accessory;
import app.repository.AccessoryRepository;
import app.web.dto.AccessoryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AccessoryService {

    private final AccessoryRepository accessoryRepository;


    public AccessoryService(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
    }


    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    public void createAccessory(AccessoryRequest createAccessoryRequest) {

        if (createAccessoryRequest.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("Invalid accessory price: {}", createAccessoryRequest.getPrice());
        }

        Accessory accessory = Accessory.builder()
                .accessoryName(createAccessoryRequest.getAccessoryName())
                .description(createAccessoryRequest.getDescription())
                .price(createAccessoryRequest.getPrice())
                .imageUrl(createAccessoryRequest.getImageUrl())
                .build();
        accessoryRepository.save(accessory);
        log.info("Accessory created successfully with ID: {}", accessory.getId());
    }

    public Accessory getAccessoryById(UUID id) {
        return accessoryRepository.findById(id).orElse(null);
    }

    public void deleteAccessory(UUID id) {
        Optional<Accessory> accessoryOptional = accessoryRepository.findById(id);
        accessoryRepository.delete(accessoryOptional.get());
    }
}
