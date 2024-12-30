package com.demo.poc.service;

import com.demo.poc.dto.PokemonDTO;
import com.demo.poc.repository.PokemonTypeRepository;
import com.demo.poc.dto.AbilityDTO;
import com.demo.poc.dto.PokemonTypeDTO;
import com.demo.poc.dto.UbigeoResponseDTO;
import com.demo.poc.repository.PokemonRepository;
import com.demo.poc.repository.AbilityRepository;
import com.google.inject.Inject;

public class UbigeoServiceImpl implements UbigeoService {

  private final PokemonRepository pokemonRepository;
  private final PokemonTypeRepository pokemonTypeRepository;
  private final AbilityRepository abilityRepository;

  @Inject
  public UbigeoServiceImpl(PokemonRepository departmentRepository,
                           PokemonTypeRepository provinceRepository,
                           AbilityRepository districtRepository) {
    this.pokemonRepository = departmentRepository;
    this.pokemonTypeRepository = provinceRepository;
    this.abilityRepository = districtRepository;
  }

  @Override
  public UbigeoResponseDTO findUbigeo(String ubigeoCode) {
    String pokemonId = ubigeoCode.substring(0, 2);
    String pokemonTypeId = ubigeoCode.substring(2, 4);
    String abilityId = ubigeoCode.substring(4, 6);

    PokemonDTO pokemon = pokemonRepository.findByPokemonId(pokemonId);
    PokemonTypeDTO pokemonType = pokemonTypeRepository.findByPokemonTypeIdAndPokemonId(pokemonTypeId, pokemonId);
    AbilityDTO ability = abilityRepository.findByAbilityIdAndPokemonTypeIdAndPokemonId(abilityId, pokemonTypeId, pokemonId);

    return UbigeoResponseDTO.builder()
        .code(ubigeoCode)
        .pokemon(pokemon)
        .pokemonType(pokemonType)
        .ability(ability)
        .build();
  }
}
