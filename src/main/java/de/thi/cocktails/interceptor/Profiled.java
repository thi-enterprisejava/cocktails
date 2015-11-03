package de.thi.cocktails.interceptor;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@InterceptorBinding
public @interface Profiled {
}
