import { Component, OnInit, Input } from '@angular/core';
import { Pokemon } from 'src/app/models/Pokemon';
// import { HttpClient } from '@angular/common/http';
import { PokemonService } from 'src/app/pokemon/services/pokemon.service';


// component for displaying each pokemon, will be used in team and box

@Component({
  selector: 'app-pokemon-card',
  templateUrl: './pokemon-card.component.html',
  styleUrls: ['./pokemon-card.component.css']
})
export class PokemonCardComponent implements OnInit {

  @Input()
  pokemon: Pokemon;


  releaseToggled = false; // variable to track if release button is visible

  constructor(private pokemonService: PokemonService) {
  }


  ngOnInit() {
  }

  // if pokemon is clicked add/remove button
  releaseToggle() {
    this.releaseToggled = !this.releaseToggled;
  }

  release(poke) {
    this.pokemonService.release(poke.id);
  }



}
