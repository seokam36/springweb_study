package spring_practice3;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {
    private final MovieRepository repository;

    // 등록
    public boolean save(MovieDto movieDto) {
        MovieEntity entity = movieDto.toEntity();
        MovieEntity saved = repository.save(entity);
        if (saved.getMovieId() >= 1){
            return true;
        }
        return false;
    }

    // 전체 조회
    public List<MovieDto> findAll() {
        List<MovieEntity> movieEntities = repository.findAll();
        ArrayList<MovieDto> movieList = new ArrayList<>();
        movieEntities.forEach((movieEntity)->{
            movieList.add(MovieDto.toDto(movieEntity));
        });
        return movieList;
    }

    // 개별 조회
    public MovieDto findById(int movieId) {
        Optional<MovieEntity> optional = repository.findById(movieId);
        if (optional.isPresent()){
            MovieEntity movieEntity = optional.get();
            MovieDto movieDto = MovieDto.toDto(movieEntity);
            return movieDto;
        }
        return null;
    }

    // 개별 수정
    public boolean update(MovieDto movieDto) {
        Optional<MovieEntity> optional = repository.findById(movieDto.getMovieId());
        if (optional.isPresent()){
            MovieEntity movieEntity = optional.get();
            movieEntity.setDirector(movieDto.getDirector());
            movieEntity.setTitle(movieDto.getTitle());
            movieEntity.setRating(movieDto.getRating());
            movieEntity.setReleasedate(movieDto.getReleasedate());
            return true;
        }
        return false;
    }

    // 삭제
    public boolean delete(int movieId) {
        Optional<MovieEntity> optional = repository.findById(movieId);
        if (optional.isPresent()) {
            MovieEntity movieEntity = optional.get();
            repository.delete(movieEntity);
            return true;
        }
        return false;
    }
}
