package example.day08;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {
    // 서비스키 안전하게 application.properties 에서 관리
    @Value("${api.public-data.serviceKey}")
    private String serviceKey;
    // 2. WebClient 객체 빌더패턴 생성
    WebClient webClient = WebClient.builder().build();

    public Map<String, Object> test1() {
        // 1. API 주소 (공공데이터 신청한 api 요청 url)
        String url = "https://api.odcloud.kr/api/15052602/v1/uddi:855807e2-fe8a-4e47-8a5a-ce1894e410d7_201909031553";
        url += "?page=" + 1;
        url += "&perPage="+10;
        url += "&serviceKey="+serviceKey;
        // 2. WebClient 객체 빌더패턴 생성
        // 3. WebClient 객체 이용한 api 요청 하고 응답받기
        Map<String, Object> response = webClient.get() // .http메소드명
                .uri(url)
                .retrieve() // 요청 결과 반환 결과 수신
                .bodyToMono(Map.class) // 응답 결과 content-type 직렬화/변환
                .block();// 동기화
        return response;
    }

    public Map<String, Object> test2() {
        // 1. API 주소
        String url = "https://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown";
        url += "?serviceKey="+serviceKey;
        url += "&pageNo="+1;
        url += "&numOfRows"+10;

        // 3. 주의할점 : webClient 에서 xml 타입을 String 타입으로 가져오기
        String response = webClient.get().uri(url).retrieve()
                .bodyToMono(String.class) // XML 타입 -> String -> Map 직렬화/변환
                .block();
        // 4. String -> xml 변환
        XmlMapper xmlMapper = new XmlMapper(); // xml매퍼 객체 생성
        try {
            Map<String , Object> map = xmlMapper.readValue(response, Map.class);
            return map;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    // [3] 프로젝트내 resources > static > 파일명.csv
    public List<Map<String, Object>> test3(){
        // 1. .csv파일 경로, resources 이하 폴더
        String fileName = "static/중소벤처기업부_벤처기업명단_20260521.csv";
        // 2. ClassPathResource 객체 이용해서 해당 경로내 파일 가져오기 [파일객체]
        ClassPathResource resource = new ClassPathResource(fileName);
        List<Map<String , Object >> list = new ArrayList<>();
        try {
            // 3. (대용량)파일들을 바이트로 읽어오기
            byte[] bytes = resource.getInputStream().readAllBytes();
            // 4. 한글 인코딩, EUC-KR, CP949, UTF-8
            InputStreamReader reader = new InputStreamReader(new java.io.ByteArrayInputStream(bytes), Charset.forName("CP949"));
            // 5. OpenCSV 이용하여 바이트들을 대입
            CSVReader csvReader = new CSVReaderBuilder(reader).build();
            // 6. 주로 첫행은 제목(행) 가져오기 (key/속성명 사용)
            String[] headers = csvReader.readNext(); // 한줄 읽어오기
            // 7. 나머지 행들은 반복문 이용해서 가져오기
            String[] values;
            while (true){
                // 8.
                values = csvReader.readNext();
                if (values == null){
                    break;
                }
                // 9. 반복문 이용해서 map 만들기
                Map<String, Object  > row = new LinkedHashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], values[i]);
                }
                // 10. list에 생성한 map 추가
                list.add(row);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Map<String, Object> test4() {
        String url = "https://apis.data.go.kr/6260000/FoodService/getFoodKr?serviceKey=133a87bf0560d1f71d28b921f55209465a418318bfbc48a90409ef98072c41ab&pageNo=1&numOfRows=10&resultType=json";
        String raw = webClient.get().uri(url).retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(raw, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
JSON VS XML VS CSV
    - JSON(자바스크립트객체) : {속성명 : 속성값, 속성명 : 속성값{
    - XML(마크업) : <속성명>속성값</속성명>
    - CSV(,쉼표구분) : 값,값,값,값
*/

