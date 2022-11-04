package be.abvv.bali.member.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class BackendException extends RuntimeException {

    private ExceptionAPIResponse exceptionAPIResponse;

    public BackendException(ExceptionAPIResponse exceptionAPIResponse) {
        super(StringUtils.join(Arrays.asList(exceptionAPIResponse.getHttpMessage(), exceptionAPIResponse.getMoreInformation()), " - "));
        this.exceptionAPIResponse = exceptionAPIResponse;
    }

    public ExceptionAPIResponse getExceptionAPIResponse() {
        return exceptionAPIResponse;
    }
}
