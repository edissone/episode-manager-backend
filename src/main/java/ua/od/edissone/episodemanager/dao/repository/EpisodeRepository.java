package ua.od.edissone.episodemanager.dao.repository;

import ua.od.edissone.episodemanager.dao.model.Episode;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends CrudRepository<Episode, Long> {
  List<Episode> findAll();
  Optional<Episode> findByTitle(String title);
}
