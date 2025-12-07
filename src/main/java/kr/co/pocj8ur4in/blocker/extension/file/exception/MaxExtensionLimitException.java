package kr.co.pocj8ur4in.blocker.extension.file.exception;

public class MaxExtensionLimitException extends RuntimeException {

    private final int maxLimit;

    public MaxExtensionLimitException(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    @Override
    public String getMessage() {
        return String.format("커스텀 확장자는 최대 %d개까지 추가할 수 있습니다.", maxLimit);
    }
}
