package spring_practice3;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@MappedSuperclass // 현재 클래스는 상속용 매핑
@EntityListeners(AuditingEntityListener.class) // 리스너 구현체 등록
public class BaseTime {
    // 1. 레코드 생성시점
    @CreatedDate
    private LocalDateTime createDate;

    // 2. 레코드 변경시점
    @LastModifiedDate
    private LocalDateTime updateDate;
}
