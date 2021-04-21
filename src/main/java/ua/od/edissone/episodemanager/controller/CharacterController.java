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
import ua.od.edissone.episodemanager.dto.CharacterDTO;
import ua.od.edissone.episodemanager.service.CharacterService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/character")
public class CharacterController {
  private final CharacterService service;

  @Autowired
  public CharacterController(CharacterService service) {
    this.service = service;
  }

  @GetMapping("/list")
  public List<CharacterDTO> list() {
    log.info("GET /api/character/list");
    return service.list();
  }

  @GetMapping("/{id}")
  public CharacterDTO get(@PathVariable Long id) {
    log.info("GET /api/episodes/get/{}", id);
    return service.get(id);
  }

  @PostMapping("/new")
  public CharacterDTO create(@RequestBody @Valid CharacterDTO dto) {
    log.info("POST /api/character/new");
    log.info("BODY: {}", dto);
    return service.create(dto);
  }

  @PutMapping("/{id}")
  public CharacterDTO update(@PathVariable Long id, @RequestBody @Valid CharacterDTO dto) {
    log.info("PUT /api/character/{}", id);
    log.info("BODY: {}", dto);
    return service.update(id, dto);
  }

  @DeleteMapping("/{id}")
  public CharacterDTO delete(@PathVariable Long id) {
    log.info("DELETE /api/character/{}", id);
    return service.delete(id);
  }
}
