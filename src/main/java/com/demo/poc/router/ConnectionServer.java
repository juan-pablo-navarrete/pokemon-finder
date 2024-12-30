package com.demo.poc.router;

import com.google.inject.Inject;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionServer {

  private final PokemonRouterTCP pokemonRouterTCP;
  private final ServerSocket serverSocket;

  @Inject
  public ConnectionServer(PokemonRouterTCP pokemonRouterTCP,
                          ServerSocket serverSocket) {
    this.pokemonRouterTCP = pokemonRouterTCP;
    this.serverSocket = serverSocket;
  }

  public void start() throws IOException {
    Socket socket;
    while (true) {
      socket = serverSocket.accept();
      pokemonRouterTCP.setSocket(socket);
      pokemonRouterTCP.start();
      log.info("A new connection was detected");
    }
  }

}
