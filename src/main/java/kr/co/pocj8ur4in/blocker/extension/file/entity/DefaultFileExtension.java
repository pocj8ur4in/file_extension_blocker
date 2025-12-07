package kr.co.pocj8ur4in.blocker.extension.file.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "default_file_extensions")
@NoArgsConstructor
@Getter
public class DefaultFileExtension extends FileExtension {
    @Column(nullable = false)
    private boolean isChecked;

    public DefaultFileExtension(String name) {
        super(name);
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }
}
