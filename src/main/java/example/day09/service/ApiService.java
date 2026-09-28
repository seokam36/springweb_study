package example.day09.service;

import example.day09.model.dto.ApiDto;
import example.day09.model.entity.ApiEntity;
import example.day09.repository.ApiRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApiService {
    private final ApiRepository apiRepository;

    public List<ApiDto> findAll() {
        List<ApiEntity> all = apiRepository.findAll();
        List<ApiDto> apiDtos = all.stream().map(apiEntity -> {
            return ApiDto.entityToDto(apiEntity);
        }).toList();
        return apiDtos;
    }

    public boolean save(ApiDto apiDto) {
        ApiEntity apiEntity = apiDto.dtoToEntity();
        apiRepository.save(apiEntity);
        return true;
    }
}
