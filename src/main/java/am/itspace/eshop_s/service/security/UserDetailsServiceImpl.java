package am.itspace.eshop_s.service.security;

import am.itspace.eshop_s.entity.User;
import am.itspace.eshop_s.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = userService.findByEmail(username);
        if (byEmail.isPresent()) {
            return new CurrentUser(byEmail.get());
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
