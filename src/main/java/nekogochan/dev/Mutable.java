package nekogochan.dev;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Target({ElementType.TYPE_USE, PARAMETER, METHOD})
@Retention(SOURCE)
public @interface Mutable {
}
