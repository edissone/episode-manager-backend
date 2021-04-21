package ua.od.edissone.episodemanager.service;

import ua.od.edissone.episodemanager.dto.CharacterDTO;

import java.util.List;

public interface CharacterService {
  List<CharacterDTO> list();

  CharacterDTO get(Long id);

  CharacterDTO create(CharacterDTO dto);

  CharacterDTO update(Long id, CharacterDTO dto);

  CharacterDTO delete(Long id);
}
