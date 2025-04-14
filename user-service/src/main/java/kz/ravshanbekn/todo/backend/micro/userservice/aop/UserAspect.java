package kz.ravshanbekn.todo.backend.micro.userservice.aop;

import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserDto;
import kz.ravshanbekn.todo.backend.micro.userservice.producer.UserCreatedEvent;
import kz.ravshanbekn.todo.backend.micro.userservice.producer.UserCreatedEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class UserAspect {

    private final UserCreatedEventProducer userCreatedEventProducer;

    @Around("execution(* kz.ravshanbekn.todo.backend.micro.userservice.controller..createUser(..))")
    public Object userInitTaskCreationAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        UserDto userDto = (UserDto) proceedingJoinPoint.proceed();
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(userDto.getId());
        userCreatedEventProducer.sendUserCreatedEvent(userCreatedEvent);
        log.info("User init tasks call executed");
        return userDto;
    }

}