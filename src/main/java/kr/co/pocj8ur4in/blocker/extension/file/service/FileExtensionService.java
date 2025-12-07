package kr.co.pocj8ur4in.blocker.extension.file.service;

import java.util.List;
import kr.co.pocj8ur4in.blocker.extension.file.controller.dto.AddCustomExtensionRequest;
import kr.co.pocj8ur4in.blocker.extension.file.controller.dto.AddDefaultExtensionRequest;
import kr.co.pocj8ur4in.blocker.extension.file.entity.CustomFileExtension;
import kr.co.pocj8ur4in.blocker.extension.file.entity.DefaultFileExtension;

public interface FileExtensionService {
    List<DefaultFileExtension> listDefaultExtensions();

    List<CustomFileExtension> listCustomExtensions();

    void saveCustomExtension(AddCustomExtensionRequest request);

    void removeCustomExtension(String extensionUuid);

    void saveDefaultExtension(AddDefaultExtensionRequest request);
}
