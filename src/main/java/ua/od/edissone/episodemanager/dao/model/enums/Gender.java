package ua.od.edissone.episodemanager.dao.model.enums;

import ua.od.edissone.episodemanager.exception.InvalidGenderException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Gender {
  MALE("Male", 'M'),
  FEMALE("Female", 'F'),
  OTHER("Other", 'O');

  private final String value;
  private final char column;

  Gender(String value, char column) {
    this.value = value;
    this.column = column;
  }

  public static Gender get(String value) {
    return Arrays.stream(values()).filter(g -> g.value.equals(value)).findFirst()
        .orElseThrow(InvalidGenderException::new);
  }

  public static Gender columnOf(char column) {
    return Arrays.stream(values()).filter(g -> g.column == column).findFirst()
        .orElseThrow(InvalidGenderException::new);
  }
}
