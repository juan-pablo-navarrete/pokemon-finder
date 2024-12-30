package com.demo.poc.commons;

import static com.demo.poc.commons.Constant.*;

import com.demo.poc.repository.PokemonRepository;
import com.demo.poc.repository.AbilityRepository;
import com.demo.poc.repository.PokemonTypeRepository;
import com.demo.poc.router.ConnectionServer;
import com.demo.poc.router.UbigeoHandler;
import com.demo.poc.router.UbigeoRouterTCP;
import com.demo.poc.service.PokemonService;
import com.demo.poc.service.PokemonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.io.IOException;
import java.net.ServerSocket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComponentInjectorConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(PropertiesReader.class);
        bind(JsonFileReader.class);
        bind(PokemonRepository.class);
        bind(PokemonTypeRepository.class);
        bind(AbilityRepository.class);
        bind(PokemonService.class).to(PokemonServiceImpl.class);
        bind(ObjectMapper.class);
        bind(UbigeoHandler.class);
        bind(UbigeoRouterTCP.class);
        bind(ServerSocket.class).toProvider(ServerSocketProvider.class);
        bind(ConnectionServer.class);
    }

    static class ServerSocketProvider implements Provider<ServerSocket> {
        private final PropertiesReader propertiesReader;

        @Inject
        public ServerSocketProvider(PropertiesReader propertiesReader) {
            this.propertiesReader = propertiesReader;
        }

        @Override
        public ServerSocket get() {
            int port = Integer.parseInt(propertiesReader.getProperty("application.port"));
            try {
                log.info(GREEN + BOLD + "Application started on port " + port + RESET);
                return new ServerSocket(port);
            } catch (IOException exception) {
                throw new RuntimeException("Error creating socket: " + exception.getMessage(), exception);
            }
        }
    }
}
