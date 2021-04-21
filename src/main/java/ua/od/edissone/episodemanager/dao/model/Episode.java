package ua.od.edissone.episodemanager.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.testcontainers.shaded.org.apache.commons.lang.builder.EqualsBuilder;
import org.testcontainers.shaded.org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "episode")
@EntityListeners(value = AuditingEntityListener.class)
public class Episode {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "episode_id")
  private Long id;

  @Column(name = "number")
  private Long number;

  @Column(name = "season")
  private Integer season;

  @Column(name = "title")
  private String title;

  @Column(name = "released_at")
  private LocalDate releasedAt;

  @Singular(value = "character", ignoreNullCollections = true)
  @ManyToMany(
      fetch = FetchType.EAGER,
      cascade = {
          CascadeType.REFRESH,
          CascadeType.PERSIST,
          CascadeType.MERGE,
          CascadeType.DETACH,

      })
  @JoinTable(
      name = "character_to_episode",
      joinColumns = { @JoinColumn(name = "episode_id") },
      inverseJoinColumns = { @JoinColumn(name = "character_id") }
  )
  private List<Character> characters;

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;

    if (!(o instanceof Episode))
      return false;

    Episode episode = (Episode) o;

    return new EqualsBuilder().append(number, episode.number)
        .append(season, episode.season).append(title, episode.title).append(releasedAt, episode.releasedAt).isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37).append(number).append(season).append(title)
        .append(releasedAt).toHashCode();
  }
}
