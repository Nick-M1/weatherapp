package com.nick.weatherapp.service.httperrors;

import com.nick.weatherapp.exceptions.InvalidApiKey;
import com.nick.weatherapp.exceptions.LocationNotFound;
import com.nick.weatherapp.exceptions.ServerSideException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;


/** Issue: RestTemplate throws error whenever Http status 4xx or 5xx is returned <p>
 *  Solution: Error handling by using a handler on ResponseTemplate */

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            throw new ServerSideException();
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {

            if (httpResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new LocationNotFound("The location entered was invalid or doesn't exist");

            } else if (httpResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new InvalidApiKey("The API Key used was invalid");

            } else if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new IllegalStateException();
            }
        }
    }
}