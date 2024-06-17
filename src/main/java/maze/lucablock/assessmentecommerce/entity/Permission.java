package maze.lucablock.assessmentecommerce.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

  SUPER_ADMIN_READ("super_admin:read"),
  SUPER_ADMIN_UPDATE("super_admin:update"),
  SUPER_ADMIN_CREATE("super_admin:create"),
  SUPER_ADMIN_DELETE("super_admin:delete"),

  ADMIN_READ("admin:read"),
  ADMIN_UPDATE("admin:update"),
  ADMIN_CREATE("admin:create"),
  ADMIN_DELETE("admin:delete"),

  USER_READ("user:read"),
  UsER_UPDATE("user:update"),
  USER_CREATE("user:create"),
  USER_DELETE("user:delete")

  ;

  @Getter
  private final String permission;
}