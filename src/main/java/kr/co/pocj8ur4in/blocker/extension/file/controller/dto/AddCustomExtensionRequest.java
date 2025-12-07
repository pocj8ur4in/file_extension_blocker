package kr.co.pocj8ur4in.blocker.extension.file.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCustomExtensionRequest {

    @NotBlank @Size(min = 1, max = 20) private String name;
}
