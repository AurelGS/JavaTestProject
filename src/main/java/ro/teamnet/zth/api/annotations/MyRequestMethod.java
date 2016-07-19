package ro.teamnet.zth.api.annotations;

import java.lang.annotation.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.METHOD})
@Retention(RUNTIME)
@Documented

public @interface MyRequestMethod {

    String methodType() default "GET";
    String urlPath();

}
