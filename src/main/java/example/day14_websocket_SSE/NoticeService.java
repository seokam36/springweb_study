package example.day14_websocket_SSE;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/*
websocket + socket : 채팅구현
SSE : 알림
*/
@Service
public class NoticeService {
    // 1. 연결된 클라이언트들의 정보 보관하는 리스트
    // * 서버가 클라이언트에게 비동기 요청 보내기 위한 HTTP 응답 객체 , 연결유지
    // * 동기/비동기 : 동기화(하나의 메소드를 순차적으로 실행) , 비동기(하나의 메소드를 동시실행)
    // * ArrayList() 동기화 지원 X -> 하나의 서버가 구독과 메시지 전송 동시 다발적으로 순차처리
    // CopyWriteArrayList() : 여러개 요청들을 동시에 접속,종료,메시지전송 동시성 제공
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    // 2. 클라이언트 구독 처리 (SseEmitter 생성하여 리스트에 저장)
    public SseEmitter subscribe(){
        SseEmitter emitter = new SseEmitter(); // 2.1 SseEmitter 객체 생성
        emitters.add(emitter); // 2.2 리스트에 저장

        // 2.4 안전하게 클라이언트 연결이 비정상이면 리스트에서 삭제
        // * 리액트에서 중복되는 구독 신청으로 문제발생
        emitter.onCompletion(()->emitters.remove(emitter));
        emitter.onTimeout(()->emitters.remove(emitter));
        emitter.onError((e)-> emitters.remove(emitter));

        return emitter;// 2.3 생성된 객체 반환
    }

    // 3. 메시지 전송 (서버가 클라이언트에게 메시지 전송)
    public void onMessage(String message){
        // 3.1 현재 리스트에 저장된 emitter 들에게 메시지 보내기
        for (SseEmitter emitter : emitters) {
            // 3.2 .send(SseEmitter.event().name("구독식별").data(내용물)
            try {
                emitter.send(SseEmitter.event().name("notice").data(message));
            } catch (IOException e) {
                // 3.3 메시지 전송 실패시 실패한 emitter 리스트에 제외
                emitters.remove(emitter);
            }
        }
    }

}
