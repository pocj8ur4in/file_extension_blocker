package kr.co.pocj8ur4in.blocker.extension.file.service;

import java.util.List;
import java.util.UUID;
import kr.co.pocj8ur4in.blocker.extension.file.controller.dto.AddCustomExtensionRequest;
import kr.co.pocj8ur4in.blocker.extension.file.controller.dto.AddDefaultExtensionRequest;
import kr.co.pocj8ur4in.blocker.extension.file.entity.CustomFileExtension;
import kr.co.pocj8ur4in.blocker.extension.file.entity.DefaultFileExtension;
import kr.co.pocj8ur4in.blocker.extension.file.exception.DuplicateExtensionException;
import kr.co.pocj8ur4in.blocker.extension.file.exception.EmptyExtensionException;
import kr.co.pocj8ur4in.blocker.extension.file.exception.ExistDefaultExtensionException;
import kr.co.pocj8ur4in.blocker.extension.file.exception.ExtensionNotFoundException;
import kr.co.pocj8ur4in.blocker.extension.file.exception.InvalidExtensionFormatException;
import kr.co.pocj8ur4in.blocker.extension.file.exception.MaxExtensionLimitException;
import kr.co.pocj8ur4in.blocker.extension.file.repository.CustomFileExtensionRepository;
import kr.co.pocj8ur4in.blocker.extension.file.repository.DefaultFileExtensionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileExtensionServiceImpl implements FileExtensionService {
    private static final int MAX_CUSTOM_EXTENSIONS = 200;

    private final CustomFileExtensionRepository customFileExtensionRepository;
    private final DefaultFileExtensionRepository defaultFileExtensionRepository;

    public FileExtensionServiceImpl(
            CustomFileExtensionRepository customFileExtensionRepository,
            DefaultFileExtensionRepository defaultFileExtensionRepository) {
        this.customFileExtensionRepository = customFileExtensionRepository;
        this.defaultFileExtensionRepository = defaultFileExtensionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DefaultFileExtension> listDefaultExtensions() {
        return defaultFileExtensionRepository.findAllByOrderByCreatedAtAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomFileExtension> listCustomExtensions() {
        return customFileExtensionRepository.findAllByOrderByCreatedAtAsc();
    }

    @Override
    public void saveCustomExtension(AddCustomExtensionRequest request) {
        String name = request.getName().trim().toLowerCase();

        if (name.isEmpty()) {
            throw new EmptyExtensionException();
        }

        if (!name.matches("^[a-zA-Z0-9]+$")) {
            throw new InvalidExtensionFormatException(request.getName().trim());
        }

        long currentCount = customFileExtensionRepository.countWithLock();
        if (currentCount >= MAX_CUSTOM_EXTENSIONS) {
            throw new MaxExtensionLimitException(MAX_CUSTOM_EXTENSIONS);
        }

        if (customFileExtensionRepository.existsByNameIgnoreCase(name)) {
            throw new DuplicateExtensionException(name);
        }

        if (defaultFileExtensionRepository.existsByNameIgnoreCase(name)) {
            throw new ExistDefaultExtensionException(name);
        }

        CustomFileExtension extension = new CustomFileExtension(name);
        customFileExtensionRepository.save(extension);
    }

    @Override
    public void removeCustomExtension(String extensionUuid) {
        UUID uuid = UUID.fromString(extensionUuid);
        CustomFileExtension extension =
                customFileExtensionRepository
                        .findByUuid(uuid)
                        .orElseThrow(() -> new ExtensionNotFoundException(extensionUuid));
        customFileExtensionRepository.delete(extension);
    }

    @Override
    public void saveDefaultExtension(AddDefaultExtensionRequest request) {
        UUID uuid = UUID.fromString(request.getExtensionUuid());
        DefaultFileExtension extension =
                defaultFileExtensionRepository
                        .findByUuid(uuid)
                        .orElseThrow(
                                () -> new ExtensionNotFoundException(request.getExtensionUuid()));
        extension.setChecked(!extension.isChecked());
        defaultFileExtensionRepository.save(extension);
    }
}
