package com.kar.book.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kar.book.model.dto.PostsDTO;
import com.kar.book.model.service.BookListService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DataController {
	private final BookListService service;

    @GetMapping("/api/events")
    public List<PostsDTO> getEvents() {
        List<PostsDTO> postList = service.findEvents();
        return postList;  // 이벤트 데이터 반환 로직
    }
}
