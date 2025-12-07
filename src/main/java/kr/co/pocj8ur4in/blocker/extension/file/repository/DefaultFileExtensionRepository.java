package kr.co.pocj8ur4in.blocker.extension.file.repository;

import kr.co.pocj8ur4in.blocker.extension.file.entity.DefaultFileExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultFileExtensionRepository extends JpaRepository<DefaultFileExtension, Long> {}
