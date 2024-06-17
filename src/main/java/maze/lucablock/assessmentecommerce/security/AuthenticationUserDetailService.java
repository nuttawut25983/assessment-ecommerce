package maze.lucablock.assessmentecommerce.security;


import maze.lucablock.assessmentecommerce.entity.User;
import maze.lucablock.assessmentecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public AuthenticationUserDetailService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException("User not found")
    );
  }

}