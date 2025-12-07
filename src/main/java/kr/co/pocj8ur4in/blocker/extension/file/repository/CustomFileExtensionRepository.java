package kr.co.pocj8ur4in.blocker.extension.file.repository;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kr.co.pocj8ur4in.blocker.extension.file.entity.CustomFileExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFileExtensionRepository extends JpaRepository<CustomFileExtension, Long> {
    Optional<CustomFileExtension> findByUuid(UUID uuid);

    boolean existsByNameIgnoreCase(String name);

    List<CustomFileExtension> findAllByOrderByCreatedAtAsc();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT COUNT(c) FROM CustomFileExtension c")
    long countWithLock();
}
