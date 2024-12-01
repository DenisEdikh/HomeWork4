package aston.profile;

import aston.model.LogMethod;
import aston.repository.LogMethodRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class ProfileAspect {
    private final LogMethodRepository logMethodRepository;

    @Pointcut("execution(* aston.controller..*(..))")
    public void controllersMethods() {
    }

    @Around("controllersMethods()")
    public Object profileMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            LogMethod logMethod = new LogMethod();
            logMethod.setDuration(duration);
            logMethod.setMethod(joinPoint.getSignature().getName());

            logMethodRepository.save(logMethod);
        }
    }
}
