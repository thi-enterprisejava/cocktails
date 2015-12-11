package de.thi.cocktails.security;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RunAs;
import javax.ejb.Stateless;
import java.util.concurrent.Callable;

@Stateless
@RunAs("user")
@PermitAll
public class AuthenticatedUser {

    public void call(Callable callable) throws Exception {
        callable.call();
    }

    public void run(Runnable runnable) throws Exception {
        runnable.run();
    }

}
