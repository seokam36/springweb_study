package spring_total.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;
import spring_total.model.dto.ReviewDto;
import spring_total.model.entity.ProductEntity;
import spring_total.model.entity.ReviewEntity;
import spring_total.repository.ProductRepository;
import spring_total.repository.ReviewRepository;

@Transactional
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    // 1. 제품별 리뷰 조회 기능 ( GET,List<ReviewDto> )
    public List<ReviewDto> 제품별조회(Integer bno){
        Optional<ProductEntity> byId = productRepository.findById(bno);
        List<ReviewDto> reviewDtos = new ArrayList<>();
        if (byId.isPresent()){
            ProductEntity productEntity = byId.get();
            productEntity.getReviewEntities().forEach(reviewEntity -> {
                reviewDtos.add(ReviewDto.entityToDto(reviewEntity));
            });
        }
        return reviewDtos;
    }

    // 2. 제품 리뷰 등록 기능 ( POST, boolean )
    public boolean 제품리뷰등록(ReviewDto reviewDto){
        ReviewEntity reviewEntity = reviewDto.dtoToEntity();
        Optional<ProductEntity> byId = productRepository.findById(reviewDto.getBno());
        if (byId.isPresent()){
            ProductEntity productEntity = byId.get();
            reviewEntity.setProductEntity(productEntity);
        }
        ReviewEntity savedEntity = reviewRepository.save(reviewEntity);
        if ( savedEntity.getRno() >= 1 ) return true;
        return false;
    }
    // 3. 제품 리뷰 삭제 기능 ( DELETE, boolean )
    public boolean 리뷰삭제( Integer rno ){
        Optional<ReviewEntity> optional = reviewRepository.findById(rno);

        if (optional.isPresent()) {
            reviewRepository.deleteById(rno);
            return true;
        } return false;
    }
}
