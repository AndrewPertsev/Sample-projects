package by.pertsev.hotel.hiber.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Aspect
public class LoggingAspect {

    @Around("@annotation(logMethod)")
    public Object logMethodExecution(ProceedingJoinPoint pjp, LogMethod logMethod) throws Throwable {
        final MethodSignature signature = (MethodSignature) pjp.getSignature();
        final Method method = signature.getMethod();
        final StopWatch stopWatch = new StopWatch();
        try {
            if (logMethod.logArguments()) {
                final String arguments = IntStream.iterate(0, i -> i + 1)
                        .limit(Math.min(signature.getParameterNames().length, pjp.getArgs().length))
                        .mapToObj(i -> signature.getParameterNames()[i] + "=" + pjp.getArgs()[i])
                        .collect(Collectors.joining(","));
                log.info("^^^^^^^^^^^^^^^^^^Start execution of {} with arguments: {}", method, arguments);
            } else {
                log.info("^^^^^^^^^^^^^^^^^^Start execution of {}", method);
            }
            stopWatch.start();
            final Object result = pjp.proceed();
            stopWatch.stop();
            log.info("^^^^^^^^^^^^^^^^^Finish execution of {} (running {} ms)", method, stopWatch.getTotalTimeNanos() / 1000000);
            return result;
        } catch (Exception ex) {
            log.info("______!______    Fail execution of {} (running {} ms)", method, stopWatch.getTotalTimeNanos() / 1000000, ex);
            throw ex;
        }
    }
}