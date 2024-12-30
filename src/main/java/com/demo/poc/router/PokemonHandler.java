package com.demo.poc.router;

import com.demo.poc.service.PokemonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.poc.dto.PokemonResponseDTO;
import com.google.inject.Inject;
import java.io.IOException;
import java.io.PrintWriter;

public class PokemonHandler {

  private final ObjectMapper objectMapper;
  private final PokemonService pokemonService;

  @Inject
  public PokemonHandler(ObjectMapper objectMapper, PokemonService pokemonService) {
    this.objectMapper = objectMapper;
    this.pokemonService = pokemonService;
  }

  public void findPokemon(String pokemonCode, PrintWriter output) throws IOException {
    PokemonResponseDTO pokemon = pokemonService.findPokemon(pokemonCode);
    String pokemonJson = objectMapper.writeValueAsString(pokemon);

    output.println(pokemonJson);
  }

}
