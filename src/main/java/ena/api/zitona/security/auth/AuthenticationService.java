package ena.api.zitona.security.auth;

import ena.api.zitona.entitys.User;
import ena.api.zitona.repositorys.UserRepository;
import ena.api.zitona.response.ResponseData;
import ena.api.zitona.security.config.JwtService;
import ena.api.zitona.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public ResponseEntity<ResponseData<AuthenticationResponse>> register(RegisterRequest request) {
//        if (userRepository.findUserByEmail(request.getEmail())) {
//            return
//        }
        var user = User.builder()
                .prenom(request.getPrenom())
                .nom(request.getNom())
                .email(request.getEmail())
                .telephone(request.getTelephone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .deviceToken(request.getDeviceToken())
                .build();

        if (userRepository.findUserByEmail(request.getEmail()).isEmpty()) {
            userRepository.save(user);
            AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwtService.generateToken(user), user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData<>(authenticationResponse, HttpStatus.CREATED, "User created successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseData<>(null, HttpStatus.OK, "Email deja exist"));

        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .user(user)
                .build();
    }
}
