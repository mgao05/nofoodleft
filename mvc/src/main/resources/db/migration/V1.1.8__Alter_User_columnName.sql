ALTER TABLE users
RENAME COLUMN isAccountNonExpired TO account_expired;
ALTER TABLE users
RENAME COLUMN isAccountNonLocked TO account_locked;
ALTER TABLE users
RENAME COLUMN isCredentialsNonExpired TO credentials_expired;
ALTER TABLE users
RENAME COLUMN isEnabled TO enabled;