package kr.co.pocj8ur4in.blocker.extension.file.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddDefaultExtensionRequest {

    @NotBlank private String extensionUuid;
}
