package backend.musinsa.controller;


import backend.musinsa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController("board")
public class BoardController {

    private final BoardService boardService;



}
