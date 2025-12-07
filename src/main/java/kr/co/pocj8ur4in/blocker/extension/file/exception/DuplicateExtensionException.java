package kr.co.pocj8ur4in.blocker.extension.file.exception;

public class DuplicateExtensionException extends RuntimeException {

    private final String extensionName;

    public DuplicateExtensionException(String extensionName) {
        this.extensionName = extensionName;
    }

    @Override
    public String getMessage() {
        return String.format("이미 등록된 커스텀 확장자입니다: %s", extensionName);
    }
}
