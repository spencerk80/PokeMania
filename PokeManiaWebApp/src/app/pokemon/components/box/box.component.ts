import { Component, OnInit, OnDestroy } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { UserService } from 'src/app/login/services/user.service';
// import { User } from 'src/app/models/User';
import { Pokemon } from 'src/app/models/Pokemon';
import { PokemonService } from 'src/app/pokemon/services/pokemon.service';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-box',
  templateUrl: './box.component.html',
  styleUrls: ['./box.component.css']
})
export class BoxComponent implements OnInit {
  // shoudlnt need these here anymore, keeping just incase
  // private httpClient: HttpClient,
  // private userService: UserService,
  constructor(private pokemonService: PokemonService) { }

  pokemon: Pokemon[] = [];
  team: Pokemon[] = [];
  teamSize = 0;


  boxSubscription: Subscription;
  teamSubscription: Subscription;
  sizeSubscription: Subscription;

  // When this page is routed to, it gets all pokemon for the user
  // this is done by subscribing to box observable from pokemon.service
  // it also gets the team so it can compare what is in the box to the team
  // and then only display what isn't in the team

  ngOnInit() {
    this.sizeSubscription = this.pokemonService.$size.subscribe(num => {
      this.teamSize = num;
    });
    this.boxSubscription = this.pokemonService.$box.subscribe(pokes => {
      this.pokemon = pokes;
      let i = 0;
      for (const poke of this.team) {
        for (const pokeB of this.pokemon) {
          if (poke.id === pokeB.id) {
            this.pokemon.splice(i, 1);
          }
          i++;
        }
        i = 0;
      }
    });

    // check is done in both getting the team and box because they are done
    // asynchronously so either could finish first
    this.teamSubscription = this.pokemonService.$team.subscribe(pokes => {
      this.team = pokes;
      let i = 0;
      for (const poke of this.team) {
        for (const pokeB of this.pokemon) {
          if (poke.id === pokeB.id) {
            this.pokemon.splice(i, 1);
          }
          i++;
        }
        i = 0;
      }
      
    });


  }

  toTeam(poke) {
    this.pokemonService.toTeam(poke, this.pokemon);
    const tempSize = this.teamSize + 1;
    this.pokemonService.sizeStream.next(tempSize);
  }

  // when component is destroyed, we dont want subscription left open
  ngOnDestroy() {
    this.boxSubscription.unsubscribe();
    this.sizeSubscription.unsubscribe();
    this.teamSubscription.unsubscribe();
  }




}
