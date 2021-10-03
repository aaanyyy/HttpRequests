package Models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data

public class Response {
    private String statusText;
    private int statusCode;
    private Map<String, String> headers;
    private String payload;
}
