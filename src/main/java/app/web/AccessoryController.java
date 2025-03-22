package app.web;

import app.model.Accessory;
import app.service.AccessoryService;
import app.web.dto.AccessoryRequest;
import app.web.dto.AccessoryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accessories")
public class AccessoryController {

    private final AccessoryService accessoryService;

    public AccessoryController(AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<AccessoryResponse> createAccessory(@RequestBody AccessoryRequest createRequest
                                                             ) {
        accessoryService.createAccessory(createRequest);
        AccessoryResponse response = new AccessoryResponse();
        BeanUtils.copyProperties(createRequest, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AccessoryResponse>> getAllAccessories() {
        List<Accessory> accessories = accessoryService.getAllAccessories();
        List<AccessoryResponse> responses = accessories.stream()
                .map(accessory -> {
                    AccessoryResponse response = new AccessoryResponse();
                    BeanUtils.copyProperties(accessory, response);
                    return response;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessoryResponse> getAccessoryById(@PathVariable UUID id) {
        Accessory accessory = accessoryService.getAccessoryById(id);
        if (accessory != null) {
            AccessoryResponse response = new AccessoryResponse();
            BeanUtils.copyProperties(accessory, response);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccessory(@PathVariable UUID id) {
        Accessory accessory = accessoryService.getAccessoryById(id);
        if (accessory != null) {
            accessoryService.deleteAccessory(id);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
