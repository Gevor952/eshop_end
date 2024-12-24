package am.itspace.eshop_s.service.Impl;

import am.itspace.eshop_s.entity.User;
import am.itspace.eshop_s.repository.UserRepository;
import am.itspace.eshop_s.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
