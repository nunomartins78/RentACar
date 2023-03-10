package academy.mindswap.rentacar.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

public class EffingStupidError implements UserDetailsChecker {
    @Override
    public void check(UserDetails toCheck) {
    }
}
