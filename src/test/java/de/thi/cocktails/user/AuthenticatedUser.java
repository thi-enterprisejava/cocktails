package de.thi.cocktails.user;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import java.util.concurrent.Callable;

@Stateless
@RunAs("user")
@PermitAll
public class AuthenticatedUser {
    public <V> V call(Callable<V> callable) throws Exception {
        return callable.call();
    }
}
