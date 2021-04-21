package ua.od.edissone.episodemanager.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ua.od.edissone.episodemanager.dao.model.Character;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends CrudRepository<Character, Long> {
  List<Character> findAll();
  Optional<Character> findByName(String name);
  List<Character> findAllByIdIn(List<Long> ids);
}
