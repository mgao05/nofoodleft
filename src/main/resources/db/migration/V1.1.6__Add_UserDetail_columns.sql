ALTER table users add column isAccountNonExpired boolean;
ALTER table users add column isAccountNonLocked boolean;
ALTER table users add column isCredentialsNonExpired boolean;
ALTER table users add column isEnabled boolean;