package kz.ravshanbekn.todo.backend.micro.taskservice.controller;

import kz.ravshanbekn.todo.backend.micro.taskservice.model.dto.StatDto;
import kz.ravshanbekn.todo.backend.micro.taskservice.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController("/stat")
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    @PostMapping("/stat")
    @ResponseStatus(HttpStatus.OK)
    public StatDto findByEmail(@RequestParam Long userId) {
        return statService.findByUserId(userId);
    }
}
