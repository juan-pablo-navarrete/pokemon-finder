package com.demo.poc.repository;

import com.demo.poc.dto.PokemonDTO;
import com.demo.poc.commons.JsonFileReader;
import com.demo.poc.commons.PropertiesReader;
import com.google.inject.Inject;
import java.util.Comparator;
import java.util.List;

public class PokemonRepository {

  private final PropertiesReader propertiesReader;
  private final JsonFileReader jsonFileReader;

  @Inject
  public PokemonRepository(PropertiesReader propertiesReader, JsonFileReader jsonFileReader) {
    this.propertiesReader = propertiesReader;
    this.jsonFileReader = jsonFileReader;
  }

  public PokemonDTO findByPokemonId(String pokemonId) {
    List<PokemonDTO> pokemons = findAll();
    for (PokemonDTO pokemon: pokemons) {
      if(pokemon.getId().equals(pokemonId))
        return pokemon;
    }
    throw new IllegalArgumentException("No such pokemon");
  }

  public List<PokemonDTO> findAll() {
    String filePath = propertiesReader.getProperty("pokemon.path");
    List<PokemonDTO> pokemons = jsonFileReader.readListFromFile(PokemonDTO.class, filePath);
    pokemons.sort(Comparator.comparing(PokemonDTO::getId));
    return pokemons;
  }
}
