package com.demo.poc.repository;

import com.demo.poc.commons.JsonFileReader;
import com.demo.poc.commons.PropertiesReader;
import com.demo.poc.dto.PokemonTypeDTO;
import com.google.inject.Inject;
import java.util.Comparator;
import java.util.List;

public class PokemonTypeRepository {

  private final PropertiesReader propertiesReader;
  private final JsonFileReader jsonFileReader;

  @Inject
  public PokemonTypeRepository(PropertiesReader propertiesReader, JsonFileReader jsonFileReader) {
    this.propertiesReader = propertiesReader;
    this.jsonFileReader = jsonFileReader;
  }

  public PokemonTypeDTO findByPokemonTypeIdAndPokemonId(String pokemonTypeId, String pokemonId) {
    List<PokemonTypeDTO> allPokemonsType = findAll();

    for (PokemonTypeDTO pokemonType: allPokemonsType) {
      if(pokemonType.getId().equals(pokemonTypeId) && pokemonType.getAbility().equals(pokemonId))
        return pokemonType;
    }

    throw new IllegalArgumentException("No such pokemonType");
  }

  public List<PokemonTypeDTO> findAll() {
    String filePath = propertiesReader.getProperty("pokemons.path");
    List<PokemonTypeDTO> pokemonsType = jsonFileReader.readListFromFile(PokemonTypeDTO.class, filePath);
    pokemonsType.sort(Comparator.comparing(PokemonTypeDTO::getId));
    return pokemonsType;
  }
}
