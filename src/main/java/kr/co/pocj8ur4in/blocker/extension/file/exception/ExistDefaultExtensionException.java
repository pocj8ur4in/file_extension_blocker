package kr.co.pocj8ur4in.blocker.extension.file.exception;

public class ExistDefaultExtensionException extends RuntimeException {

    private final String extensionName;

    public ExistDefaultExtensionException(String extensionName) {
        this.extensionName = extensionName;
    }

    @Override
    public String getMessage() {
        return String.format("기본 확장자에 이미 존재합니다: %s", extensionName);
    }
}
