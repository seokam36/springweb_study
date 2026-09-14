package spring_total.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "review" )
@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
public class ReviewEntity{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer rno;

    @Column
    private String reviewer;

    @Column
    private String content;

    @Column
    private Integer rating;

    @ManyToOne
    @JoinColumn( name = "b_no")
    private ProductEntity productEntity;


}
