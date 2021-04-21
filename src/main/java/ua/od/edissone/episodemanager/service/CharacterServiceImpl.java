package ua.od.edissone.episodemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.edissone.episodemanager.dao.model.Character;
import ua.od.edissone.episodemanager.dao.model.enums.Gender;
import ua.od.edissone.episodemanager.dao.repository.CharacterRepository;
import ua.od.edissone.episodemanager.dto.CharacterDTO;
import ua.od.edissone.episodemanager.exception.CharacterExistsException;
import ua.od.edissone.episodemanager.exception.CharacterNotFoundException;
import ua.od.edissone.episodemanager.util.mapper.CharacterMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {
  private final CharacterRepository repository;
  private final CharacterMapper mapper;

  @Autowired
  public CharacterServiceImpl(CharacterRepository repository,
      CharacterMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override public List<CharacterDTO> list() {
    return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
  }

  @Override public CharacterDTO get(Long id) {
    return mapper.toDTO(repository.findById(id).orElseThrow(CharacterNotFoundException::new));
  }

  @Override public CharacterDTO create(CharacterDTO dto) {
    Optional<Character> actual = repository.findByName(dto.getName());
    if (actual.isPresent()) {
      throw new CharacterExistsException();
    }
    return mapper.toDTO(repository.save(mapper.toEntity(dto)));
  }

  @Override public CharacterDTO update(Long id, CharacterDTO dto) {
    Character updatable = repository.findById(id).orElseThrow(CharacterNotFoundException::new);

    updatable.setName(dto.getName());
    updatable.setGender(Gender.get(dto.getGender()));
    updatable.setOrigin(dto.getOrigin());

    return mapper.toDTO(repository.save(updatable));
  }

  @Override public CharacterDTO delete(Long id) {
    Character deletable = repository.findById(id).orElseThrow(CharacterNotFoundException::new);
    repository.delete(deletable);
    return mapper.toDTO(deletable);
  }
}
