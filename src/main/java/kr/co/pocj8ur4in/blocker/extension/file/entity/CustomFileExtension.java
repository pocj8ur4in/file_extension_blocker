package kr.co.pocj8ur4in.blocker.extension.file.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "custom_file_extensions")
@NoArgsConstructor
public class CustomFileExtension extends FileExtension {
    public CustomFileExtension(String name) {
        super(name);
    }
}
