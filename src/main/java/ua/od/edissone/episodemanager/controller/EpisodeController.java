package ua.od.edissone.episodemanager.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.od.edissone.episodemanager.dto.EpisodeDTO;
import ua.od.edissone.episodemanager.service.EpisodeService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {
  private final EpisodeService service;

  @Autowired
  public EpisodeController(EpisodeService service) {
    this.service = service;
  }

  @GetMapping("/list")
  public List<EpisodeDTO> list() {
    log.info("GET /api/episodes/list");
    return service.list();
  }

  @GetMapping("/{id}")
  public EpisodeDTO get(@PathVariable Long id) {
    log.info("GET /api/episodes/get/{}", id);
    return service.get(id);
  }

  @PostMapping("/new")
  public EpisodeDTO create(@RequestBody @Valid EpisodeDTO dto) {
    log.info("POST /api/episodes/new");
    log.info("BODY: {}", dto);
    return service.create(dto);
  }

  @PutMapping("/{id}")
  public EpisodeDTO update(@PathVariable Long id, @RequestBody @Valid EpisodeDTO dto) {
    log.info("PUT /api/episodes/{}", id);
    log.info("BODY: {}", dto);
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public EpisodeDTO delete(@PathVariable Long id) {
    log.info("DELETE /api/episodes/{}", id);
    return service.delete(id);
  }
}
