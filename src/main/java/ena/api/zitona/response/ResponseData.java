package ena.api.zitona.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    private T data;
    private HttpStatus httpStatus;
    private String message;
}