package it.olegna.spring.integration.mail.be.util;

import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

public class FileDownloadUtils {

    private HttpHeaders headers;
    private InputStream is;

    public FileDownloadUtils() {
        this.headers = new HttpHeaders();
    }

    public FileDownloadUtils(HttpHeaders headers, InputStream is) {
        this.headers = headers;
        this.is = is;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }
    public void addHeader( String headerKey, String headerValue ){
        if(!StringUtils.hasText(headerKey)){
            throw  new IllegalArgumentException("Passato un header key nullo o vuoto");
        }
        if(!StringUtils.hasText(headerValue)){
            throw  new IllegalArgumentException("Passato un header value nullo o vuoto");
        }
        this.headers.add(headerKey, headerValue);
    }
}
