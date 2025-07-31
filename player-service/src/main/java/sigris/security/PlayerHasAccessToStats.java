package sigris.security;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import sigris.Model.PlayersDTO.PlayerDTO;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class PlayerHasAccessToStats {

    @Before("@annotation(sigris.security.isOpenStats)")
    public void playerHasAccessToStats(JoinPoint joinPoint) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String nickname = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg instanceof String)
                .findFirst()
                .map(String.class::cast)
                .orElseThrow(() -> new IllegalArgumentException("Nickname not found"));

//TODO remark for improving project if user and account are friends and account type "friendsOnly" we can share stats

        boolean isAdmin = user.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !nickname.equals(user.getUsername())) {
            throw new AccessDeniedException("No access");
        }
    }
}
