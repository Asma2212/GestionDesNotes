package tn.encar.gestnotes.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.encar.gestnotes.exceptions.UnauthorizedAccessException;
import tn.encar.gestnotes.services.impl.AuthService;

@Component
@Aspect
public class SecurityAspect {

    @Autowired
    private AuthService authService;

    @Before("execution(* tn.encar.gestnotes.services.impl.EnseignantService.*(..)) || " +
            "execution(* tn.encar.gestnotes.services.impl.AdminService.*(..))")
    public void checkAuthorization(JoinPoint joinPoint) {
        if (!authService.isUserAuthenticated()) {
            throw new UnauthorizedAccessException("L'utilisateur n'est pas authentifié");
        }
    }
}
