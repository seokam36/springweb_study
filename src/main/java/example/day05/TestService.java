package example.day05;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TestService {
    private final TestRepository repository;

    // 1. 전체 조회
    public List<TestDto> findAll() {
        // 1. 모든 엔티티 조회
        List<TestEntity> entities = repository.findAll();
        // 2. 모든 엔티티 -> DTO 변환
        List<TestDto> list = new ArrayList<>();
        // 모든 엔티티 반복해서 모든 DTO로 변환해서 새로운 리스트에 저장
        entities.forEach((entity) -> {
            list.add(TestDto.from(entity));
        });
        return list;
    }

    // 2. 등록
    public boolean save(TestDto testDto) {
        // 1. dto를 entity로 변환
        TestEntity entity = testDto.toEntity();
        // 2. 변환된 entity save로 저장
        TestEntity saved = repository.save(entity);
        // 3. 저장된 entity PK 넘버가 1이상이면 true 반환
        if (saved.getNo() >= 1) {
            return true;
        }
        return false;
    }


    public boolean update(TestDto testDto) {
        Optional<TestEntity> optional = repository.findById(testDto.getNo());
        if (optional.isPresent()){
            TestEntity entity = optional.get();
            entity.setPrice(testDto.getPrice());
            entity.setDescri(testDto.getDescri());
            return true;
        }
        return false;
    }
}
