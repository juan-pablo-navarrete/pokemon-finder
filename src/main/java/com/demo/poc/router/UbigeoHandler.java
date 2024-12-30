package com.demo.poc.router;

import com.demo.poc.service.PokemonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.poc.dto.PokemonResponseDTO;
import com.google.inject.Inject;
import java.io.IOException;
import java.io.PrintWriter;

public class UbigeoHandler {

  private final ObjectMapper objectMapper;
  private final PokemonService ubigeoService;

  @Inject
  public UbigeoHandler(ObjectMapper objectMapper, PokemonService ubigeoService) {
    this.objectMapper = objectMapper;
    this.ubigeoService = ubigeoService;
  }

  public void findUbigeo(String ubigeoCode, PrintWriter output) throws IOException {
    PokemonResponseDTO ubigeo = ubigeoService.findPokemon(ubigeoCode);
    String ubigeoJson = objectMapper.writeValueAsString(ubigeo);

    output.println(ubigeoJson);
  }

}
