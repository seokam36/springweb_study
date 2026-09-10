package spring_test.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_test.model.dto.CommentDto;
import spring_test.model.entity.BoardEntity;
import spring_test.model.entity.CommentEntity;
import spring_test.repository.BoardRepository;
import spring_test.repository.CommentRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public boolean save(CommentDto commentDto) {
        CommentEntity commentEntity = commentDto.dtoToEntity();
        Optional<BoardEntity> byId = boardRepository.findById(commentDto.getBoardId());
        if (byId.isPresent()){
            BoardEntity boardEntity = byId.get();
            commentEntity.setBoardEntity(boardEntity);
            commentRepository.save(commentEntity);
            return true;
        }
        return false;
    }

    public boolean delete(Integer commentId, String password) {
        Optional<CommentEntity> byId = commentRepository.findById(commentId);
        if (byId.isPresent()){
            CommentEntity commentEntity = byId.get();
            if (commentEntity.getPassword().equals(password)){
                commentRepository.deleteById(commentId);
                return true;
            }
        }
        return false;
    }
}
