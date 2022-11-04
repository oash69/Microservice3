package be.abvv.bali.member.business;

import be.abvv.bali.member.exception.BackendException;
import be.abvv.bali.member.exception.ExceptionAPIResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class DefaultErrorHandler implements ResponseErrorHandler {
    private ObjectMapper jacksonObjectMapper;

    public DefaultErrorHandler() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        this.jacksonObjectMapper = objectMapper;
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        if(response != null && response.getStatusCode() != null){
            return !response.getStatusCode().equals(HttpStatus.OK);
        }
        else{
            return false;
        }
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        byte[] src = IOUtils.toByteArray(response.getBody());
        ExceptionAPIResponse exceptionAPIResponse = jacksonObjectMapper.readValue(src, ExceptionAPIResponse.class);
        throw new BackendException(exceptionAPIResponse);
    }
}
