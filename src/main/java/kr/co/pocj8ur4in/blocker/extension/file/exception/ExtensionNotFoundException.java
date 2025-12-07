package kr.co.pocj8ur4in.blocker.extension.file.exception;

public class ExtensionNotFoundException extends RuntimeException {

    private final String extensionUuid;

    public ExtensionNotFoundException(String extensionUuid) {
        this.extensionUuid = extensionUuid;
    }

    @Override
    public String getMessage() {
        return String.format("존재하지 않는 확장자입니다: %s", extensionUuid);
    }
}
