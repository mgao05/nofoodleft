ALTER table users add column isAccountNonExpired BIT default 1;
ALTER table users add column isAccountNonLocked BIT default 1;
ALTER table users add column isCredentialsNonExpired BIT default 1;
ALTER table users add column isEnabled BIT default 1;