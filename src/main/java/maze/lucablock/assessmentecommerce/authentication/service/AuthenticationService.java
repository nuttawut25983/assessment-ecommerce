package maze.lucablock.assessmentecommerce.authentication.service;

import maze.lucablock.assessmentecommerce.authentication.response.AuthenticationResponse;
import maze.lucablock.assessmentecommerce.authentication.request.AuthenticationRequest;
import maze.lucablock.assessmentecommerce.authentication.request.RegisterRequest;

public interface AuthenticationService {

  AuthenticationResponse loginAdmin(AuthenticationRequest request);

  AuthenticationResponse login(AuthenticationRequest request);


  AuthenticationResponse register(RegisterRequest request);
}
