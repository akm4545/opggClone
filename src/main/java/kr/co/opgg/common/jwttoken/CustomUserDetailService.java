package kr.co.opgg.common.jwttoken;

import kr.co.opgg.apis.user.dto.UserResponse;
import kr.co.opgg.common.exception.UserException;
import kr.co.opgg.datasource.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse userResponse = Optional.ofNullable(userRepository.findByUserId(username))
                .orElseThrow(UserException.UserInfoNotFoundException::new);
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUserIdx(userResponse.getUserIdx());
        return userDetails;
    }
}
