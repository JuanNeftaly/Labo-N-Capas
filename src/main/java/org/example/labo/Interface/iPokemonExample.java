package org.example.labo.Interface;

import org.example.labo.Domain.DTO.Pokemon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokemon-example", url = "https://pokeapi.co/api/v2/pokemon")
public interface iPokemonExample {

    @GetMapping("/{pokemonName}")
    public Pokemon getPokemon(@PathVariable("pokemonName") String pokemonName);

}