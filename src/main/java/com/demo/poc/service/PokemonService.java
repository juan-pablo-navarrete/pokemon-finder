package com.demo.poc.service;

import com.demo.poc.dto.PokemonResponseDTO;

public interface PokemonService {

  PokemonResponseDTO findPokemon(String pokemonCode);
}
