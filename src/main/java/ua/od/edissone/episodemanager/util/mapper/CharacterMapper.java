package ua.od.edissone.episodemanager.util.mapper;

import org.springframework.stereotype.Component;
import ua.od.edissone.episodemanager.dao.model.Character;
import ua.od.edissone.episodemanager.dao.model.enums.Gender;
import ua.od.edissone.episodemanager.dto.CharacterDTO;

@Component
public class CharacterMapper {
  public CharacterDTO toDTO(Character entity) {
    return CharacterDTO.builder()
        .id(entity.getId())
        .gender(entity.getGender().getValue())
        .name(entity.getName())
        .origin(entity.getOrigin())
        .build();
  }

  public Character toEntity(CharacterDTO dto){
    return Character.builder()
        .id(dto.getId())
        .name(dto.getName())
        .gender(Gender.get(dto.getGender()))
        .origin(dto.getOrigin())
        .build();
  }
}
