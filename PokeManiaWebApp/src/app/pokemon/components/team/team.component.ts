import { Component, OnInit, OnDestroy } from '@angular/core';
import { PokemonService } from '../../services/pokemon.service';
import { Subscription } from 'rxjs';
import { Pokemon } from 'src/app/models/Pokemon';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {


  constructor(private pokemonService: PokemonService) { }

  pokemon: Pokemon[] = []; // team
  teamSize = 0;

  teamSubscription: Subscription;
  sizeSubscription: Subscription;

  // When this page is routed to, it gets the user's team
  // this is done by subscribing to the team observable from pokemon.service


  ngOnInit() {

    this.sizeSubscription = this.pokemonService.$size.subscribe(num => {
      this.teamSize = num;
    });

    this.teamSubscription = this.pokemonService.$team.subscribe(pokes => {
      this.pokemon = pokes;
    });

  }

  toBox(poke) {
    this.pokemonService.toBox(poke, this.pokemon);
    const tempSize = this.teamSize - 1;
    this.pokemonService.sizeStream.next(tempSize);
  }

  // when component is destroyed, we dont want subscription left open
  ngOnDestroy() {
    this.teamSubscription.unsubscribe();
    this.sizeSubscription.unsubscribe();

  }




}
