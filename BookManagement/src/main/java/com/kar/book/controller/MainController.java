package com.kar.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kar.book.model.dto.PostsDTO;
import com.kar.book.model.service.BookListService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller
@RequiredArgsConstructor

public class MainController {

    private final BookListService service;

    @RequestMapping("/")
    public String mainPage() {
        return "/common/main";  // main페이지로 포워드
    }
}


