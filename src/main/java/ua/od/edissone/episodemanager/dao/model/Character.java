package ua.od.edissone.episodemanager.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.testcontainers.shaded.org.apache.commons.lang.builder.EqualsBuilder;
import org.testcontainers.shaded.org.apache.commons.lang.builder.HashCodeBuilder;
import ua.od.edissone.episodemanager.dao.model.enums.Gender;
import ua.od.edissone.episodemanager.util.converter.GenderConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "character_table")
@EntityListeners(value = AuditingEntityListener.class)
public class Character {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "character_id")
  private Long id;

  @Column(name = "character_name")
  private String name;

  @Convert(converter = GenderConverter.class)
  private Gender gender;

  @Column(name = "origin")
  private String origin;

  @ManyToMany
  @JoinTable(
      name = "character_to_episode",
      joinColumns = { @JoinColumn(name = "character_id") },
      inverseJoinColumns = { @JoinColumn(name = "episode_id") }
  )
  private List<Episode> episodes;

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;

    if (!(o instanceof Character))
      return false;

    Character character = (Character) o;

    return new EqualsBuilder().append(name, character.name)
        .append(gender, character.gender).append(origin, character.origin).isEquals();
  }

  @Override public int hashCode() {
    return new HashCodeBuilder(17, 37).append(name).append(gender).append(origin)
        .toHashCode();
  }
}
