package spring_activity1.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_activity1.Dto.ProductLogDto;
import spring_activity1.Entity.ProductEntity;
import spring_activity1.Entity.ProductLogEntity;
import spring_activity1.Repository.ProductLogRepository;
import spring_activity1.Repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductLogService {
    private final ProductLogRepository productLogRepository;
    private final ProductRepository productRepository;

    // 로그 저장
    public boolean save(ProductLogDto productLogDto) {
        // 받은 dto -> Entity로 변환 logNo랑 productEntity는 비어있는 상태
        ProductLogEntity productLogEntity = productLogDto.dtoToEntity();
        // Dto의 productNo값을 사용해 findById 메소드 사용해서 해당 No의 productEntity객체 찾기
        Optional<ProductEntity> byId = productRepository.findById(productLogDto.getProduct_no());
        if (byId.isPresent()){
            ProductEntity productEntity = byId.get();
            // 찾은 productEntity객체를 비어있는 productLogEntity에 setter 사용해서 채우기
            productLogEntity.setProductEntity(productEntity);
            productLogRepository.save(productLogEntity);
            return true;
        }
        return false;
    }

    // 로그 전체 조회
    public List<ProductLogDto> findAll() {
        List<ProductLogEntity> all = productLogRepository.findAll();
        ArrayList<ProductLogDto> list = new ArrayList<>();

        all.forEach((entity)->{
            list.add(ProductLogDto.entityToDto(entity));
        });
        return list;
    }

    // 로그 개별 조회
    public ProductLogDto findById(int no) {
        Optional<ProductLogEntity> byId = productLogRepository.findById(no);
        if (byId.isPresent()){
            ProductLogEntity productLogEntity = byId.get();
            return ProductLogDto.entityToDto(productLogEntity);
        }
        return null;
    }

    // 로그 수정
    public boolean update(ProductLogDto productLogDto) {
        // 수정할 productLog 찾기
        Optional<ProductLogEntity> byId = productLogRepository.findById(productLogDto.getProductlog_no());
        if (byId.isPresent()){
            ProductLogEntity productLogEntity = byId.get();
            if (productLogDto.getProduct_qty() != null){
                productLogEntity.setProduct_qty(productLogDto.getProduct_qty());
            }
            if (productLogDto.getProduct_condition() != null){
                productLogEntity.setProduct_condition(productLogDto.getProduct_condition());
            }
            if (productLogDto.getCustomerlog_day() != null){
                productLogEntity.setCustomerlog_day(productLogDto.getCustomerlog_day());
            }
            if (productLogDto.getProductlog_price() != null){
                productLogEntity.setProductlog_price(productLogDto.getProductlog_price());
            }
            // product_no 값을 넘겼는지 안넘겼는지 확인 , 넘겼으면 해당 no값으로 product 객체를 찾아서 세팅
            if (productLogDto.getProduct_no() != null){
                Optional<ProductEntity> productById = productRepository.findById(productLogDto.getProduct_no());
                if (productById.isPresent()){
                    productLogEntity.setProductEntity(productById.get());
                }
            }
            return true;
        }
        return false;
    }

    // 로그 삭제
    public boolean delete(int no) {
        return false;
    }
}
