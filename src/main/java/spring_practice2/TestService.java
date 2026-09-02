package spring_practice2;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository repository;

    // 저장
    public boolean save(TestEntity testEntity) {
        TestEntity savedEntity = repository.save(testEntity);
        if (savedEntity.getNo() >= 1){
            return true;
        }
        return false;
    }

    // 모든 리스트 조회
    public List<TestEntity> findAll() {
        return repository.findAll();
    }

    // 개별 리스트 조회
    public Optional<TestEntity> findById(int no) {
        return repository.findById(no);
    }

    // 리스트 삭제
    public boolean delete(int no) {
        repository.deleteById(no);
        return true;
    }

    // 리스트 업데이트
    @Transactional
    public boolean update(TestEntity testEntity) {
        Optional<TestEntity> optional = repository.findById(testEntity.getNo());
        if (optional.isPresent()){
            TestEntity savedEntity = optional.get();
            savedEntity.setContent(testEntity.getContent());
            return true;
        }
        return false;
    }
}
