package ua.od.edissone.episodemanager.util.converter;

import ua.od.edissone.episodemanager.dao.model.enums.Gender;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderConverter implements AttributeConverter<Gender, Character> {

  @Override public Character convertToDatabaseColumn(Gender gender) {
    return gender.getColumn();
  }

  @Override public Gender convertToEntityAttribute(Character character) {
    return Gender.columnOf(character);
  }
}
