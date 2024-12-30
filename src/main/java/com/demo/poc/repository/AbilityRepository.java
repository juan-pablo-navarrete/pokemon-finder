package com.demo.poc.repository;

import com.demo.poc.commons.JsonFileReader;
import com.demo.poc.commons.PropertiesReader;
import com.demo.poc.dto.AbilityDTO;
import com.google.inject.Inject;
import java.util.Comparator;
import java.util.List;

public class AbilityRepository {

  private final PropertiesReader propertiesReader;
  private final JsonFileReader jsonFileReader;

  @Inject
  public AbilityRepository(PropertiesReader propertiesReader, JsonFileReader jsonFileReader) {
    this.propertiesReader = propertiesReader;
    this.jsonFileReader = jsonFileReader;
  }

  public AbilityDTO findByAbilityIdAndPokemonTypeIdAndPokemonId(String abilityId, String pokemonTypeId, String pokemonId) {
    List<AbilityDTO> allSkills = findAll();

    for (AbilityDTO ability: allSkills) {
      if(ability.getId().equals(abilityId) && ability.getPokemonType().equals(pokemonTypeId) && ability.getPokemon().equals(pokemonId))
        return ability;
    }

    throw new IllegalArgumentException("No such ability");
  }

  public List<AbilityDTO> findAll() {
    String filePath = propertiesReader.getProperty("Skills.path");
    List<AbilityDTO> skills = jsonFileReader.readListFromFile(AbilityDTO.class, filePath);
    skills.sort(Comparator.comparing(AbilityDTO::getId));
    return skills;
  }

}
