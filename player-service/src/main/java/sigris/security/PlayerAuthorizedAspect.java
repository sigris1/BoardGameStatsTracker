package sigris.security;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PlayerAuthorizedAspect {

    @Before("@annotation(sigris.security.isAuthorized)")
    public void isAuthorized(JoinPoint joinPoint) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new SecurityException("User not authenticated");
        }

        String requestedNickname = (String) joinPoint.getArgs()[0];

        if (!auth.getName().equals(requestedNickname)) {
            throw new SecurityException("You can only request your own email");
        }
    }
}
