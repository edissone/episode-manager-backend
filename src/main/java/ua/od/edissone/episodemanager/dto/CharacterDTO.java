package ua.od.edissone.episodemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterDTO {
  private Long id;

  @NotNull
  private String name;
  @NotNull
  private String gender;
  private String origin;
  private List<EpisodeDTO> episodes;

  @Override public String toString() {
    return "CharacterDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", gender='" + gender + '\'' +
        ", origin='" + origin + '\'' +
        '}';
  }
}
