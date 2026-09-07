package spring_practice5.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_practice5.dto.CommentDto;
import spring_practice5.entity.BoardEntity;
import spring_practice5.entity.CommentEntity;
import spring_practice5.repository.BoardRepository;
import spring_practice5.repository.CommentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
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

    public boolean delete(Integer commentsId, String password) {
        Optional<CommentEntity> byId = commentRepository.findById(commentsId);
        if (byId.isPresent()){
            CommentEntity commentEntity = byId.get();
            if (commentEntity.getPassword().equals(password)){
                commentRepository.deleteById(commentsId);
                return true;
            }
            return false;
        }
        return false;
    }
}
