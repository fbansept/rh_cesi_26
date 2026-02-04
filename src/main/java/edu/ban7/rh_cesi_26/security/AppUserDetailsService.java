package edu.ban7.rh_cesi_26.security;

import edu.ban7.rh_cesi_26.dao.AppUserDao;
import edu.ban7.rh_cesi_26.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    final AppUserDao appUserDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AppUser> optionalAppUser = appUserDao.findByEmail(email);

        if (optionalAppUser.isEmpty()) {
            throw new UsernameNotFoundException(email);
        }

        return new AppUserDetails(optionalAppUser.get());
    }
}
