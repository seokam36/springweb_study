package example.day14_websocket_SSE;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequestMapping("api/sse")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NoticeController {
    private final NoticeService noticeService;
    // 1. 알림 구독 매핑
    // * 응답 Content-type JSON이 아닌 스트림 타입으로 변경
    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(){
        return noticeService.subscribe();
    }
    // 2. 알림 메시지 전송 *테스트*
    @GetMapping("/message")
    public void onMessage(@RequestParam String message){
        noticeService.onMessage(message);
    }
}
