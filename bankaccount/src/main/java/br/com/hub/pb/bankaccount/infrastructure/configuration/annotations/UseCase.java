package br.com.hub.pb.bankaccount.infrastructure.configuration.annotations;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface UseCase {
}
