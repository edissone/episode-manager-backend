package ua.od.edissone.episodemanager.util.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.od.edissone.episodemanager.dto.EpisodeDTO;
import ua.od.edissone.episodemanager.dao.model.Episode;
import java.util.stream.Collectors;

@Component
public class EpisodeMapper {

  private final CharacterMapper characterMapper;

  @Autowired
  public EpisodeMapper(CharacterMapper characterMapper) {
    this.characterMapper = characterMapper;
  }

  public EpisodeDTO toDTO(Episode entity, boolean detail) {
    return EpisodeDTO.builder()
        .id(entity.getId())
        .number(entity.getNumber())
        .releasedAt(entity.getReleasedAt())
        .season(entity.getSeason())
        .title(entity.getTitle())
        .characters(
            detail ? entity.getCharacters().stream().map(characterMapper::toDTO).collect(Collectors.toList()) : null
        )
        .build();
  }

  public Episode toEntity(EpisodeDTO dto) {
    return Episode.builder()
        .title(dto.getTitle())
        .number(dto.getNumber())
        .releasedAt(dto.getReleasedAt())
        .season(dto.getSeason())
        .characters(dto.getCharacters() != null && !dto.getCharacters().isEmpty() ?
            dto.getCharacters().stream().map(characterMapper::toEntity).collect(Collectors.toList()) : null)
        .build();
  }
}
