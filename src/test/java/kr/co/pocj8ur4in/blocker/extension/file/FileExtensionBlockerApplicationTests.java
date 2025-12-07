package kr.co.pocj8ur4in.blocker.extension.file;

import kr.co.pocj8ur4in.blocker.extension.file.config.TestcontainersConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfig.class)
class FileExtensionBlockerApplicationTests {

    @Test
    void contextLoads() {}
}
