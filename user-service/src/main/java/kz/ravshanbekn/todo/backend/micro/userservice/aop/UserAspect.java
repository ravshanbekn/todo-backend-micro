package kz.ravshanbekn.todo.backend.micro.userservice.aop;

import kz.ravshanbekn.todo.backend.micro.userservice.client.TaskServiceClient;
import kz.ravshanbekn.todo.backend.micro.userservice.model.dto.user.UserDto;
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

    private final TaskServiceClient taskServiceClient;

    @Around("execution(* kz.ravshanbekn.todo.backend.micro.userservice.controller..createUser(..))")
    public Object userInitTaskCreationAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        UserDto userDto = (UserDto) proceedingJoinPoint.proceed();
        taskServiceClient.addInitTasks(userDto.getId());
        log.info("User init tasks call executed");
        return userDto;
    }

}