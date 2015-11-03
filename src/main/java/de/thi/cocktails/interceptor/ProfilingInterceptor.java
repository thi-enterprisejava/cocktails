package de.thi.cocktails.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Profiled
@Interceptor
public class ProfilingInterceptor {

    @AroundInvoke
    public Object profile(InvocationContext ic) throws Exception {
        long start = System.currentTimeMillis();
        try {
            return ic.proceed();
        } finally {
            long end = System.currentTimeMillis();
            System.out.println(String.format("Dauer des Aufrufs %s: %s ms", ic.getMethod().getName(), end-start));
        }
    }

}
