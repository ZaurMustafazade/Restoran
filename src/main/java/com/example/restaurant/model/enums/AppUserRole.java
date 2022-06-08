package com.example.restaurant.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
  ROLE_ADMIN, ROLE_WAITER;

  public String getAuthority() {
    return name();
  }

}
