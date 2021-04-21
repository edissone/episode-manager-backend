package ua.od.edissone.episodemanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EpisodeDTO {
  private Long id;
  @NotNull
  private String title;
  @NotNull
  private Integer season;
  @NotNull
  private Long number;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate releasedAt;
  private List<CharacterDTO> characters;

  @Override public String toString() {
    return "EpisodeDTO{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", season=" + season +
        ", number=" + number +
        ", releasedAt=" + releasedAt +
        ", characters=" + characters +
        '}';
  }
}
