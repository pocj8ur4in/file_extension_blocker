package kr.co.pocj8ur4in.blocker.extension.file.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import kr.co.pocj8ur4in.blocker.extension.file.entity.CustomFileExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFileExtensionRepository extends JpaRepository<CustomFileExtension, Long> {
    Optional<CustomFileExtension> findByUuid(UUID uuid);

    boolean existsByNameIgnoreCase(String name);

    List<CustomFileExtension> findAllByOrderByCreatedAtAsc();
}
