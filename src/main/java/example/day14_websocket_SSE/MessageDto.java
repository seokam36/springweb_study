package example.day14_websocket_SSE;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MessageDto {
    private String type; // 메시지 형식
    private String roomId; // 방번호
    private String sender; // 보낸 사람
    private String content; // 보낸 내용
    private String date; // 보낸 시간

}
