package spring_practice5.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_practice5.dto.BoardDto;
import spring_practice5.dto.CommentDto;
import spring_practice5.entity.BoardEntity;
import spring_practice5.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public boolean save(BoardDto boardDto) {
        BoardEntity boardEntity = boardDto.dtoToEntity();
        boardRepository.save(boardEntity);
        return true;
    }

    public List<BoardDto> findAll() {
        List<BoardEntity> all = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        all.forEach(boardEntity -> {
            BoardDto boardDto = BoardDto.entityToDto(boardEntity);
            boardEntity.getCommentEntities().forEach(commentEntity -> {
                CommentDto commentDto = CommentDto.entityToDto(commentEntity);
                boardDto.getComments().add(commentDto);
            });
            boardDtos.add(boardDto);
        });
        return boardDtos;
    }

    public boolean delete(Integer id, String password) {
        Optional<BoardEntity> byId = boardRepository.findById(id);
        if (byId.isPresent()){
            BoardEntity boardEntity = byId.get();
            if (boardEntity.getPassword().equals(password)){
                boardRepository.deleteById(id);
                return true;
            }
            return false;
        }
        return false;
    }
}
