import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/login/services/user.service';
import { PokemonService } from '../../services/pokemon.service';
import { User } from 'src/app/models/User';
import { Pokemon } from 'src/app/models/Pokemon';

@Component({
  selector: 'app-catch',
  templateUrl: './catch.component.html',
  styleUrls: ['./catch.component.css']
})
export class CatchComponent implements OnInit {

  constructor(private pokemonService: PokemonService, private userService: UserService) { }
  pokemon: Pokemon;
  user: User;
  userSubscription = this.userService.$user.subscribe(element => {
    this.user = element;
  });

  ngOnInit() {
    // check if time is >= 24 hours worth of ms to reset counter
    if ((Date.now() - this.user.cTime) >= 86400000 && this.user.counter !== 0) {
      this.pokemonService.resetCounter(this.user.id);
    }
  }

async findPoke() {
  this.pokemon = await this.pokemonService.findPoke();
}

catchPoke() {
  this.pokemonService.catchPoke(this.pokemon);
  this.pokemon = null;
}

}
