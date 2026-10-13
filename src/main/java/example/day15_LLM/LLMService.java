package example.day15_LLM;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/*
    LLM : 대규모 언어 모델 => AI 모델
    LLM API : 각 LLM 회사에서 API 제공 , API(유료/무료)
*/

@Service
@RequiredArgsConstructor
public class LLMService {
    @Value("${api.gemini-key}")
    public String geminiApiKey; // 서비스키 불러오기

    private final DeliveryRepository deliveryRepository;
    private final WebClient webClient = WebClient.builder().build(); // api 호출 라이브러리
    private ObjectMapper objectMapper = new ObjectMapper(); // 직렬화 라이브러리

    // 1. 프롬프트 요청해서 응답
    public String LLMCall(String prompt){
        // 1) 프롬프트 구성
        Map<String, Object> requestBody = Map.of("contents", List.of(Map.of("parts",List.of(Map.of("text",prompt)))));

        // 2) WebClient 이용해서 LLM API 모델 호출
        Map<String, Object> response = webClient.post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash-lite:generateContent?key={key}", geminiApiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve().bodyToMono(Map.class)
                .block();

        JsonNode jsonNode = objectMapper.valueToTree(response);// MAP -> JSON
        JsonNode testNode = jsonNode.at("/candidates/0/content" +
                "/parts/0/text");
        System.out.println(response);
        return testNode.asText();
    }

    // 2. 프롬프트 요청하여 응답받기
    public String chat1(String prompt){
        return LLMCall(prompt);
    }

    // 3. 데이터베이스내 자료 + 프롬프트 조합
    public String chat2(){
        // 1) 로그 엔티티 모두 꺼내기
        List<DeliveryLog> logs = deliveryRepository.findAll();
        try {
            // 2) 엠티티 -> JSON 변환
            String jsonContext = objectMapper.writeValueAsString(logs);
            // 3. 프롬프트 작성 -> String prompt = """  """ + 데이터
            String prompt = """
                배송 및 반품 로그 데이터이며 데이터들을 분석하여 아래와 같이 보고서 작성하기
                1. 배송 일수가 길거나 반품률이 높은 지점 식별
                2. 파손, 배송 지연, 단순 변심 등 반품 패턴을 분석
                3. 배송사 별로 통계(개수, 평균, 반품비율)
                """ + jsonContext;
            return LLMCall(prompt);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    // 4. 프롬프트 연결(질문->답변->질문->답변)
    public String chat3(String topic){
        // 1) 프롬프트1 작성
        //
        String prompt1 = String.format("""
                고객 배송 불만 접수 내용 : %s
                위 내용에서 1. 귀책 사유 2. 상품 피해 심각도(상/중/하) 판단해 3줄요약해줘
                """, topic);
        String result1 = LLMCall(prompt1);

        // 2) 프롬프트2 작성
        String prompt2 = String.format("""
                접수 내용 : %s
                1차 분석 결과 : %s
                위 분석을 토대로 배송티이 구체적인 조치 방안을 제안해줘
                """, topic, result1);
        String result2 = LLMCall(prompt2);

        // 3) 프롬프트3 작성
        String prompt3 = String.format("""
                1차 분석 : %s
                2차 조치 방안 : %s
                위 내용으로 고객에게 보낼 친절한 메시지 초안 작성
                """, result1, result2);
        return LLMCall(prompt3);
    }

    // 4. 프롬프트 의도를 판단 하여 기능 라우팅
    public String chat4(String message){
        String prompt = String.format("""
                배송 CS 로그 서비스 이며, 고객의 요청을 분석하여 다음 4가지 action_type 중 하나를 선택한다.
                JSON형식만으로 반환한다.
                [선택 가능한 ACTION_TYPE]
                - TRACK_DELIVERY : 배송 위치 추적 ,도착 예정일, 어디쯤 왔는지 확인
                - RETURN_REQUEST : 반품시청, 단순 변심 회수, 교환 문의
                - CLAIM_COMPENSATION : 배송지연 보상, 상품 파손 보상, 사고 배상 문의
                - NONE : 위 3가지 해당 하지 않거나 알 수 없는 경우 
                [JSON 출력 형식]
                {"action_type" : "TRACK_DELIVERY, "order_id" : "추출한 주문번호(예:ORD-202601-001), 없으면 빈문자열" ,"reason":"분류이유"} 
                고객 요청 : "%s"            
                """ , message);

        // 2. 프롬프트 전달하여 결과받기
        String response = LLMCall(prompt);
        try{// 3. MAP -> JSON 변환
            JsonNode root = objectMapper.readTree( response );
            // 4. JSON내 "action_type" 추출 , root.path("속성명") : 속성명에 따른 속성값 추출
            String action_type = root.path("action_type").asText("NONE"); // 없으면 NONE
            // 5. JSON내 "order_id" 추출
            String orderId = root.path("order_id").asText("");
            // 6. action_type 과 order_id 가지고 각종 서비스 라우팅/연결분기
            if( action_type.equals("TRACK_DELIVERY")){
                return processOrder( orderId, "고객이 배송 상태 조회를 요청. 위 주문 기록을 확인하고 현재 위치와 배송예정상태를 친절하게 안내해줘.");
            }else if( action_type.equals("RETURN_REQUEST")){
                return processOrder( orderId, "고객이 반품을 요청, 위 주문 기록과 사유를 확인하고 반품 수거 절차 안내해줘." );
            }else if( action_type.equals("CLAIM_COMPENSATION")){
                return processOrder( orderId, "고객이 배송지연 및 파손에 대한 보상 요청, 위 주문 기록의 배송일 수 와 근거로 보상 기준 해당 여부 및 접수 절차 안내해줘.");
            }else{ return "요청을 파악하지 못했습니다. 다시 요청해주세요"; }
        }catch(Exception e ){ System.out.println( e );}
        return null;
    }

    public String processOrder(String order_id , String prompt){
        DeliveryLog deliveryLog = deliveryRepository.findById(order_id).orElse(null);
        if (deliveryLog == null){
            return "주문번호 없습니다";
        }
        // 1. 주문번호 -> String(JSON) 변환
        String jsonLog = objectMapper.writeValueAsString(deliveryLog);
        // 2. 프롬프트 작성
        String requestPrompt = String.format("""
                [주문 정보 ] %s
                [요청 사항] %s
                """, jsonLog, prompt); // DB 정보/엔티티와 요청사항들을 프롬프트 작성
        return LLMCall(requestPrompt);
    }
}
