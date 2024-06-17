package maze.lucablock.assessmentecommerce.entity;


import static maze.lucablock.assessmentecommerce.entity.Permission.ADMIN_CREATE;
import static maze.lucablock.assessmentecommerce.entity.Permission.ADMIN_DELETE;
import static maze.lucablock.assessmentecommerce.entity.Permission.ADMIN_READ;
import static maze.lucablock.assessmentecommerce.entity.Permission.ADMIN_UPDATE;
import static maze.lucablock.assessmentecommerce.entity.Permission.USER_CREATE;
import static maze.lucablock.assessmentecommerce.entity.Permission.USER_DELETE;
import static maze.lucablock.assessmentecommerce.entity.Permission.USER_READ;
import static maze.lucablock.assessmentecommerce.entity.Permission.UsER_UPDATE;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



@Getter
@RequiredArgsConstructor
public enum Role {
  SUPER_ADMIN(
      Set.of(
          ADMIN_READ,
          ADMIN_UPDATE,
          ADMIN_DELETE,
          ADMIN_CREATE
      )
  ),
  ADMIN(
      Set.of(
          ADMIN_READ,
          ADMIN_UPDATE,
          ADMIN_DELETE,
          ADMIN_CREATE
      )
  ),
  USER(
      Set.of(
          USER_READ,
          USER_CREATE,
          UsER_UPDATE,
          USER_DELETE
      )
  );



  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
        .stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}


