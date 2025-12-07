package kr.co.pocj8ur4in.blocker.extension.file.exception;

public class InvalidExtensionFormatException extends RuntimeException {

    private final String extensionName;

    public InvalidExtensionFormatException(String extensionName) {
        this.extensionName = extensionName;
    }

    @Override
    public String getMessage() {
        return String.format("확장자는 영문자와 숫자만 입력 가능합니다: %s", extensionName);
    }
}
