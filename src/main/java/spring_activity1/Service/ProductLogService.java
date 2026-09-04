package spring_activity1.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_activity1.Dto.ProductLogDto;
import spring_activity1.Entity.ProductEntity;
import spring_activity1.Entity.ProductLogEntity;
import spring_activity1.Repository.ProductLogRepository;
import spring_activity1.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductLogService {
    private final ProductLogRepository productLogRepository;
    private final ProductRepository productRepository;

    // 로그 저장
    public boolean save(ProductLogDto productLogDto) {

    }

    // 로그 전체 조회
    public List<ProductLogDto> findAll() {
        return null;
    }

    // 로그 개별 조회
    public List<ProductLogDto> findById(int no) {
        return null;
    }

    // 로그 수정
    public boolean update(ProductLogDto productLogDto) {
        return false;
    }

    // 로그 삭제

    public boolean delete(int no) {
        return false;
    }
}
