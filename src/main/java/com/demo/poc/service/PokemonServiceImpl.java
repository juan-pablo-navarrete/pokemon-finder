package com.demo.poc.service;

import com.demo.poc.dto.PokemonDTO;
import com.demo.poc.repository.PokemonTypeRepository;
import com.demo.poc.dto.AbilityDTO;
import com.demo.poc.dto.PokemonTypeDTO;
import com.demo.poc.dto.PokemonResponseDTO;
import com.demo.poc.repository.PokemonRepository;
import com.demo.poc.repository.AbilityRepository;
import com.google.inject.Inject;

public class PokemonServiceImpl implements PokemonService {

  private final PokemonRepository pokemonRepository;
  private final PokemonTypeRepository pokemonTypeRepository;
  private final AbilityRepository abilityRepository;

  @Inject
  public PokemonServiceImpl(PokemonRepository departmentRepository,
                            PokemonTypeRepository provinceRepository,
                            AbilityRepository districtRepository) {
    this.pokemonRepository = departmentRepository;
    this.pokemonTypeRepository = provinceRepository;
    this.abilityRepository = districtRepository;
  }

  @Override
  public PokemonResponseDTO findPokemon(String pokemonCode) {
    String pokemonId = pokemonCode.substring(0, 2);
    String pokemonTypeId = pokemonCode.substring(2, 4);
    String abilityId = pokemonCode.substring(4, 6);

    PokemonDTO pokemon = pokemonRepository.findByPokemonId(pokemonId);
    PokemonTypeDTO pokemonType = pokemonTypeRepository.findByPokemonTypeIdAndPokemonId(pokemonTypeId, pokemonId);
    AbilityDTO ability = abilityRepository.findByAbilityIdAndPokemonTypeIdAndPokemonId(abilityId, pokemonTypeId, pokemonId);

    return PokemonResponseDTO.builder()
        .code(pokemonCode)
        .pokemon(pokemon)
        .pokemonType(pokemonType)
        .ability(ability)
        .build();
  }
}
