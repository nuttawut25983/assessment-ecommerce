package maze.lucablock.assessmentecommerce.authentication.service.impl;

import maze.lucablock.assessmentecommerce.authentication.response.AuthenticationResponse;
import maze.lucablock.assessmentecommerce.authentication.request.AuthenticationRequest;
import maze.lucablock.assessmentecommerce.authentication.request.RegisterRequest;
import maze.lucablock.assessmentecommerce.authentication.service.AuthenticationService;
import maze.lucablock.assessmentecommerce.entity.Role;
import maze.lucablock.assessmentecommerce.exceptions.NotFoundException;
import maze.lucablock.assessmentecommerce.security.JwtService;
import maze.lucablock.assessmentecommerce.entity.User;
import maze.lucablock.assessmentecommerce.exceptions.BadRequestException;
import maze.lucablock.assessmentecommerce.user.repository.UserRepository;
import java.time.ZonedDateTime;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
      JwtService jwtService, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }



  @Override
  public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    } catch (Exception e) {
      throw new BadRequestException("Invalid username or password");
    }
    User user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(
        () -> new NotFoundException("User not found")
    );
    return new AuthenticationResponse(jwtService.generateToken(user),ZonedDateTime.now());
  }


  @Override
  public AuthenticationResponse register(RegisterRequest request) {
    userRepository.findDistinctByUsername(request.getUsername()).ifPresent(
        user -> {
          throw new BadRequestException("User already exists");
        }
    );

    User user = new User(
        request.getUsername(),
        passwordEncoder.encode(request.getPassword()),
        Role.SUPER_ADMIN
    );
    userRepository.save(user);
    return new AuthenticationResponse(jwtService.generateToken(user), ZonedDateTime.now());
  }
}
