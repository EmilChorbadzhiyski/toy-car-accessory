package app.repository;

import app.model.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, UUID> {
}
