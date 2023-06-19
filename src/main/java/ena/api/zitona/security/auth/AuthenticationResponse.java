package ena.api.zitona.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import ena.api.zitona.entitys.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    User user;
}
