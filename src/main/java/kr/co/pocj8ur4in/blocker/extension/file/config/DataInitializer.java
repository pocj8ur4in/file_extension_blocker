package kr.co.pocj8ur4in.blocker.extension.file.config;

import java.util.List;
import kr.co.pocj8ur4in.blocker.extension.file.entity.DefaultFileExtension;
import kr.co.pocj8ur4in.blocker.extension.file.repository.DefaultFileExtensionRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements ApplicationRunner {

    private final DefaultFileExtensionRepository defaultFileExtensionRepository;

    private static final List<String> DEFAULT_EXTENSIONS =
            List.of("bat", "cmd", "com", "cpl", "exe", "scr", "js");

    public DataInitializer(DefaultFileExtensionRepository defaultFileExtensionRepository) {
        this.defaultFileExtensionRepository = defaultFileExtensionRepository;
    }

    @Override
    @Transactional
    public void run(@NonNull ApplicationArguments args) {
        for (String extensionName : DEFAULT_EXTENSIONS) {
            if (!defaultFileExtensionRepository.existsByNameIgnoreCase(extensionName)) {
                defaultFileExtensionRepository.save(new DefaultFileExtension(extensionName));
            }
        }
    }
}
