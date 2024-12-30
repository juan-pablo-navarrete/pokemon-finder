package com.demo.poc.router;

import com.google.inject.Inject;
import java.io.*;
import java.net.Socket;

public class PokemonRouterTCP extends Thread {

  private final PokemonHandler pokemonHandler;
  private Socket socket;

  @Inject
  public PokemonRouterTCP(PokemonHandler pokemonHandler) {
    this.pokemonHandler = pokemonHandler;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    try(
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true)
    ) {
      String operation = inputReader.readLine();

      if(operation.matches("^pokemon/\\d{6}$")) {
        String pokemonCode = operation.split("/")[1].trim();
        pokemonHandler.findPokemon(pokemonCode, outputWriter);
      }

    } catch (Exception exception) {
      throw new IllegalArgumentException("Operation not found: " + exception.getMessage());

    } finally {
      try {
        if (socket != null) socket.close();
      } catch (Exception exception) {
        throw new RuntimeException("Error closing TCP connection: " + exception.getMessage());
      }
    }
  }

}
