package example.day16_crawling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    크롤링 도구/라이브러리 선택
    [1] 정적페이지 (모든자료가 한번에 표시) vs 동적페이지 (구분별 자료들이 순차적 표시)
                JSOUP                               playwrite, selenium
    [2] 도메인 뒤에 robots.txt 검색해서 allow, disallow 판단
    [3] 크롤링할 선택자/식별자 찾기 , 크롤링할 페이지에서 F12 -> ctrl+shift+c 크롤링 하고싶은 위치 선택해 개발자도구에서 해당 html 찾기
    [4] ip차단 방지 하고자 user-agent 와 적절한 대기시간
*/

@Service
public class CrawService {
    // * 크롤링 실행 시 클라이언트 정보 포함 , F12 -> network
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/155.0.0.0 Safari/537.36";

    // [1] 정적페이지 크롤링1
    public List<String> craw1(){
        // 1. 크롤링 페이지 지정
        String url = "https://www.karnews.or.kr/news/articleList.html?sc_section_code=S1N1&view_type=sm";

        // 2. JSOUP 객체 생성 , Jsoup.connect(크롤링할주소).userAgent(USER_AGENT).get()
        try {
            Document document = Jsoup.connect(url).userAgent(USER_AGENT).get();
            // 3. 가져온 html 안에서 식별자 내 값만 추출 , document.select(선택자)
            // 요소1개 추출 = Element , 요소2개이상 추출 = Elements
            Elements select = document.select(".titles > a");

            // 4. 가져온 것들을 반복문 이용해서 리스트에 담기
            List<String> list = new ArrayList<>();
            for (Element element : select) {
                // 5. 마크업 사이에 값 추출 : .text()
                list.add(element.text());
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 2. 정적페이지 크롤링2
    public List<Map<String, Object>> craw2(){
        String url = "https://www.yes24.com/product/category/daybestseller";
        try {
            Document document = Jsoup.connect(url).userAgent(USER_AGENT).get();
            // .gd_name 도서명 , .txt_num>.yes_b 가격, .img_bdr img 이미지
            Elements nameList = document.select(".gd_name");
            Elements priceList = document.select(".txt_num > .yes_b");
            Elements imgList = document.select(".img_bdr img");

            List<Map<String, Object>> list = new ArrayList<>();
            for (int index = 0; index < nameList.size() ; index++) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", nameList.get(index).text()); // "name" 키로 index번째 도서명 값 저장
                map.put("price", priceList.get(index).text()); // "price" 키로 index번째 가격 값 저장
                map.put("img", imgList.get(index).attr("data-original")); // "img" 키로 index번째 이미지 값 저장
                // .text() 마크업 사이의 텍스트추출 , .attr("특정속성명") 특정속성명의 속성추출
                list.add(map);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
