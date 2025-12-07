package kr.co.pocj8ur4in.blocker.extension.file.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@Getter
public abstract class FileExtension extends BaseColumn {
    @NotBlank @Column(nullable = false, length = 20, unique = true)
    private String name;

    protected FileExtension(String name) {
        this.name = name;
    }
}
