package liga.medical.medicalmonitoring.core.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.service.LoggingService;
import lombok.RequiredArgsConstructor;
import model.LogRestMessage;
import model.SystemType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAdvice {

    private final LoggingService loggingService;

    @Pointcut(value = "@annotation(liga.medical.medicalmonitoring.core.aop.annotations.DbLog)")
    public void restLogPointcut() {
    }

    @Around(value = "restLogPointcut()")
    public Object restLogger(ProceedingJoinPoint pjp) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String logId = "rest-logger";
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String args = objectMapper.writeValueAsString(pjp.getArgs());

        LogRestMessage logMessage = LogRestMessage.builder()
                .logId(logId)
                .className(className)
                .methodName(methodName)
                .args(args)
                .build();
        loggingService.log(logMessage, SystemType.MESSAGE_ANALYZER);

        Object object = null;
        try {
            object = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            loggingService.logException(logMessage, SystemType.MESSAGE_ANALYZER);
        }
        return object;
    }
}
