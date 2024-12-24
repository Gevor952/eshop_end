package am.itspace.eshop_s.service;

import am.itspace.eshop_s.entity.User;

import java.util.Optional;

public interface UserService {
    public User save(User user);

    public Optional<User> findByEmail(String email);
}
