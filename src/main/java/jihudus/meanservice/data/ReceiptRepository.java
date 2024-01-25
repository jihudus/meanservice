package jihudus.meanservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {

    Set<Receipt> findByClient_IdAndDateBetween(UUID id, LocalDate dateStart, LocalDate dateEnd);
}