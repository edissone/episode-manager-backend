package ua.od.edissone.episodemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.edissone.episodemanager.dao.model.Character;
import ua.od.edissone.episodemanager.dao.model.Episode;
import ua.od.edissone.episodemanager.dao.repository.CharacterRepository;
import ua.od.edissone.episodemanager.dao.repository.EpisodeRepository;
import ua.od.edissone.episodemanager.dto.EpisodeDTO;
import ua.od.edissone.episodemanager.exception.EpisodeExistsException;
import ua.od.edissone.episodemanager.exception.EpisodeNotFoundException;
import ua.od.edissone.episodemanager.util.mapper.EpisodeMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EpisodeServiceImpl implements EpisodeService {
  private final CharacterRepository characterRepository;
  private final EpisodeRepository repository;
  private final EpisodeMapper mapper;

  @Autowired
  public EpisodeServiceImpl(CharacterRepository characterRepository,
      EpisodeRepository repository,
      EpisodeMapper mapper) {
    this.characterRepository = characterRepository;
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override public List<EpisodeDTO> list() {
    return repository.findAll().stream().map(e -> mapper.toDTO(e, false)).collect(Collectors.toList());
  }

  @Override public EpisodeDTO get(Long id) {
    return mapper.toDTO(repository.findById(id).orElseThrow(EpisodeNotFoundException::new), true);
  }

  @Override public EpisodeDTO create(EpisodeDTO dto) {
    Optional<Episode> actual = repository.findByTitle(dto.getTitle());
    if (actual.isPresent()) {
      throw new EpisodeExistsException();
    }
    Episode episode = mapper.toEntity(dto);
    episode.setCharacters(mapToPersistCharacters(episode.getCharacters()));
    return mapper.toDTO(repository.save(episode), true);
  }

  @Override public EpisodeDTO update(Long id, EpisodeDTO dto) {
    Episode updatable = repository.findById(id).orElseThrow(EpisodeNotFoundException::new);
    Episode update = mapper.toEntity(dto);

    updatable.setNumber(update.getNumber());
    updatable.setCharacters(mapToPersistCharacters(update.getCharacters()));
    updatable.setSeason(update.getSeason());
    updatable.setTitle(update.getTitle());
    updatable.setReleasedAt(update.getReleasedAt());

    return mapper.toDTO(repository.save(updatable), true);
  }

  @Override public EpisodeDTO delete(Long id) {
    Episode deletable = repository.findById(id).orElseThrow(EpisodeNotFoundException::new);
    repository.delete(deletable);
    return mapper.toDTO(deletable, true);
  }

  private List<Character> mapToPersistCharacters(List<Character> list) {
    List<Character> characters = list.stream().filter(c -> c.getId() == null)
        .collect(Collectors.toList());
    List<Long> ids = list.stream().filter(c -> c.getId() != null).map(Character::getId)
        .collect(Collectors.toList());
    characters.addAll(characterRepository.findAllByIdIn(ids));
    return characters;
  }
}
