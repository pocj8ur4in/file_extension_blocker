package kr.co.pocj8ur4in.blocker.extension.file.exception;

public class EmptyExtensionException extends RuntimeException {

    public EmptyExtensionException() {}

    @Override
    public String getMessage() {
        return "확장자를 입력해주세요.";
    }
}
