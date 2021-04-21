package ua.od.edissone.episodemanager.service;

import ua.od.edissone.episodemanager.dto.EpisodeDTO;

import java.util.List;

public interface EpisodeService {
  List<EpisodeDTO> list();

  EpisodeDTO get(Long id);

  EpisodeDTO create(EpisodeDTO dto);

  EpisodeDTO update(Long id, EpisodeDTO dto);

  EpisodeDTO delete(Long id);
}
