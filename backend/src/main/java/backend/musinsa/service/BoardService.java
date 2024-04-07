package backend.musinsa.service;


import backend.musinsa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public void getBoard(){
        //게시판 조회
    }

}
