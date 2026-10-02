package example.day13;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FileService {
    // [1] 업로드 경로 설정
    // 1. 현재 프로젝트의 최상위 폴더찾기
    // 2. 최상위 폴더 이후로 build 폴더로 업로드할 경로 지정
    // src폴더 : 실행전 폴더로 개발자가 코드 작성하는 폴더
    // build폴더 : 실행후 폴더로 개발자가 작성한 코드 실행한 결과물 폴더
    // * 일반사용자들은 업로드할 경우 개발자폴더가 아닌 서버폴더에 업로드 해야한다
    // * 추후 AWS(클라우드) 경우 에는 클라우드 IP
    private String baseDir = System.getProperty("user.dir");
    private String uploadPath = baseDir + "/build/resources/main/static/upload/";

    // [2] 업로드
     public String fileUpload(MultipartFile multipartFile){
         // 1. 업로드할 파일의 MultipartFile 인터페이스 가져오기
         // 2. 만약 업로드 파일 없으면 취소
         if (multipartFile == null || multipartFile.isEmpty()) return null;

         // 3. 만약 업로드 폴더가 없으면 폴더 생성 , File 객체 -> 자바가 운영체제의 파일 조작 클래스
         File dir = new File(uploadPath); // 설정한 경로 File 객체에 대입
         if (!dir.exists()) dir.mkdir(); // 설정한 경로에 폴더 없으면 폴더 생성

         // 4. 업로드할 파일명이 중복 방지 -> 1]UUID , 2]업로드날짜/시간 , 3]PK 등등 식별 추가
         // 서로 다른 사람이 같은 파일명으로 업로드 한 경우 다른 파일 취급
         // _역할은 uuid 와 실제파일명 구분용도, 파일명에 _ 존재하면 replaceAll을 통해 -로 치환
         String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename()
                 .replace("_","-");

         // 5. 업로드 , .transferTo(업로드할 file 객체);
         try {
             multipartFile.transferTo(new File(uploadPath + fileName));
             return fileName;
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
    // [3] 다운로드
    // C드라이브파일 -> JAVA -> 브라우저
    public void fileDownload(String fileName, HttpServletResponse response){
         // 1. 다운로드할 파일명과 HTTP응답객체 가져오기
        // 2. 다운로드할 파일명과 업로드 경로 조합
        String downloadPath = uploadPath + fileName;

        // 3. 만약에 파일이 없으면
        File file = new File(downloadPath);
        if (!file.exists()) return;

        try {
            // 4. 파일 있으면 읽어오기 , FileInputStream
            long fileSize = file.length(); // 파일(바이트) 용량 확인
            byte[] bytes = new byte[(int)fileSize]; // 파일 용량만큼 바이트 배열 생성

            FileInputStream fileInputStream = new FileInputStream(downloadPath); // 파일입력객체
            fileInputStream.read(bytes); // 파일입력객체가 입력온 바이트들을 바이트배열에 저장
            fileInputStream.close(); // 스트림간 버퍼 안전하게 제거

            // 6. 다운로드 형식 지정 : 브라우저 마다 상이
            // 실제 파일명으로 찾기 , UUID_파일명 -> UUID제거
            String realFileName = fileName.split("_")[1]; // _로 쪼개서 1의 인덱스
            // HTTP 헤더에 다운로드 형식 지정 , 한글 지원 X URLEncoder.encode
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(realFileName, "UTF-8"));

            // 5. 서버로 가져온 파일(바이트들)을 HTTP 응답 , 현재 다운로드 요청한 서블릿의 출력스트림 가져오기
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(bytes); // 서블릿출력스트림 객체로 앞전에 읽어온 파일바이트배열 내보내기
            servletOutputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // [4] 파일 삭제
    public boolean fileDelete(String fileName){
         // 1. 삭제할 파일명과 경로 조합
        String deleteFilePath = uploadPath + fileName;
        // 2. 경로에 파일 존재
        File file = new File(deleteFilePath);
        if (file.exists()){
            file.delete(); // 파일 삭제 함수
            return true;
        } else {
            return false;
        }
    }

}
