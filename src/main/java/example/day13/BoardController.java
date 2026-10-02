

package example.day13;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // React 로컬 포트 허용
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;
    private final FileService fileService;

    // 등록
    @PostMapping("/write")
    public boolean write(@ModelAttribute BoardDto dto) {
        return boardService.boardWrite(dto);
    }

    // 전체 조회
    @GetMapping("/list")
    public List<BoardDto> list() {
        return boardService.boardFindAll();
    }

    // 개별 조회
    @GetMapping("/view")
    public BoardDto view(@RequestParam ( name = "id") Long id) {
        return boardService.boardFindById(id);
    }

    // 다운로드
    @GetMapping("/download/{id}")
    public void download(@PathVariable(name = "id") Long id, HttpServletResponse response){
        // 1. 다운로드 받을 게시물번호와 HTTP응답객체 가져옴
        // 2. 다운로드 받을 게시물번호의 업로드파일명 조회
        String fileName = boardService.getFileName(id);

        // 3. 첨부파일이 존재하면 다운로드 진행
        if (fileName != null){
            fileService.fileDownload(fileName, response);
        }
    }
}
