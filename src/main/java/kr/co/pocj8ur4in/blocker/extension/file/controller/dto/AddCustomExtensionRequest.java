package kr.co.pocj8ur4in.blocker.extension.file.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCustomExtensionRequest {

    @NotBlank @Size(min = 1, max = 20) @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "확장자는 영문자와 숫자만 입력 가능합니다.") private String name;
}
