import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from 'src/app/login/services/user.service';
import { User } from 'src/app/models/User';
import { Pokemon } from 'src/app/models/Pokemon';
import { ReplaySubject, Subject } from 'rxjs';
import { PokemonModule } from '../pokemon.module';
import { Team } from 'src/app/models/Team';
import { Router } from '@angular/router';

// this service is used to get pokemon from DB for team and box
// also used to send from box and to team
// used to put pokemon from catch component into box

@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  sizeStream = new ReplaySubject<number>(1);
  $size = this.sizeStream.asObservable();
  // used to track number of pokemon in team,
  // if it = 6 then can't move from box into team

  boxStream = new ReplaySubject<Pokemon[]>(1);
  $box = this.boxStream.asObservable();

  teamStream = new ReplaySubject<Pokemon[]>(1);
  $team = this.teamStream.asObservable();

  user: User;
  userSubscription = this.userService.$user.subscribe(element => {
    this.user = element;
  });



  constructor(private httpClient: HttpClient, private userService: UserService, private router: Router) {

    if (!this.user) {
      this.router.navigateByUrl('/login');
    }

    // user id is hard coded in for now, should use this
    this.httpClient.get<Pokemon[]>(`http://localhost:8080/PokeManiaAPI/api/pokemon?userId=${this.user.id}`, {
      withCredentials: true
    })
      .subscribe(data => {
        this.boxStream.next(data);
      }, err => {
        console.log(err);
      });


    this.httpClient.get<Pokemon[]>(`http://localhost:8080/PokeManiaAPI/api/pokemonteam?userId=${this.user.id}`, {
      withCredentials: true
    })
      .subscribe(data => {
        this.teamStream.next(data);
        this.sizeStream.next(data.length);

      }, err => {
        console.log(err);
      });

  }

  // get the current team then add the new pokemon to it then update both streams.
  toTeam(poke, pokeBox) {

    // first get the team then add the new pokemon onto the end
    let pokeTeam: Pokemon[];
    const teamSubscription = this.$team.subscribe(pokes => {
      pokeTeam = pokes;
    });
    pokeTeam.push(poke);
    this.teamStream.next(pokeTeam);
    teamSubscription.unsubscribe();


    // now we need to put user id and poke ids into the "team" object

    let teamObject: Team;
    let nums: number[] = new Array(6);

    // tslint:disable-next-line: no-shadowed-variable
    for (let i = 0; i < 6; i++) {
      if (pokeTeam[i]) {
        nums[i] = pokeTeam[i].id;
      } else {
        nums[i] = 0;
      }
    }

    teamObject = new Team(this.user.id, nums[0], nums[1], nums[2], nums[3], nums[4], nums[5]);
    // http request to add to team in DB
    this.httpClient.post(`http://localhost:8080/PokeManiaAPI/api/pokemonteam?userId=${this.user.id}`, teamObject, {
      withCredentials: true
    }).subscribe(
      data => {
        console.log('added to team');
      },
      err => {
        console.log(err);
      }
    );

    // remove pokemon going to Team
    let i = 0;
    pokeBox.forEach(element => {
      if (element === poke) {
        pokeBox.splice(i, 1);
      }
      i++;
    });
    this.boxStream.next(pokeBox);
  }

  // get box, then add pokemon to it (remove from team) then update both streams.
  toBox(poke, pokeTeam) {

    // first get the box then add the new pokemon onto the end
    let pokeBox: Pokemon[];
    const boxSubscription = this.$box.subscribe(pokes => {
      pokeBox = pokes;
      pokeBox.push(poke);
    });
    this.boxStream.next(pokeBox); // send data to stream so components will update
    boxSubscription.unsubscribe(); // subscription no longer need



    // remove pokemon going to box
    let i = 0;
    pokeTeam.forEach(element => {
      if (element === poke) {
        pokeTeam.splice(i, 1);
      }
      i++;
    });
    this.teamStream.next(pokeTeam);

    // now we need to put user id and poke ids into the "team" object

    let teamObject: Team;
    let nums: number[] = new Array(6);

    // tslint:disable-next-line: no-shadowed-variable
    for (let i = 0; i < 6; i++) {
      if (pokeTeam[i]) {
        nums[i] = pokeTeam[i].id;
      } else {
        nums[i] = 0;
      }
    }

    teamObject = new Team(this.user.id, nums[0], nums[1], nums[2], nums[3], nums[4], nums[5]);

    // http request to remove poke from team in DB
    this.httpClient.post(`http://localhost:8080/PokeManiaAPI/api/pokemonteam?userId=${285}`, teamObject, {
      withCredentials: true
    }).subscribe(
      data => {
        console.log('removed from team');
      },
      err => {
        console.log(err);
      }
    );

  }

  async findPoke(): Promise<Pokemon> {

    const dexId: number = Math.ceil(Math.random() * 810); // get random poke 1-810
    this.user.counter = this.user.counter + 1;
    let poke: Pokemon;
    this.user.cTime = Date.now();

    this.httpClient.put(`http://localhost:8080/PokeManiaAPI/api/updatecounter`, this.user, {
      withCredentials: true
    }).subscribe(
      data => {
        console.log('counter incremented');
        this.userService.setUser(this.user);
      },
      err => {
        console.log(err);
      }
    );

    // get pokemon from api
    // this.httpClient.get<Pokemon>(`https://pokeapi.co/api/v2/pokemon/${dexId}`, {
    //   withCredentials: true
    // }).subscribe(
    //   data => {
    //     return poke = data;
    //   },
    //   err => {
    //     console.log(err);
    //   }
    // );
    let pokemon: Pokemon;
    let response: any;
    let type1: string;
    let type2: string;


    try {
      response = await this.httpClient.get(`https://pokeapi.co/api/v2/pokemon/${dexId}`).toPromise();
    } catch (err) {
      console.error(err);
      return;
    }
    if (response.types[0].slot === 1) {
      type1 = response.types[0].type.name;
      type1 = type1.charAt(0).toUpperCase() + type1.substring(1);
    } else {
      type1 = response.types[1].type.name;
      type2 = response.types[0].type.name;
      type1 = type1.charAt(0).toUpperCase() + type1.substring(1);
      type2 = type2.charAt(0).toUpperCase() + type2.substring(1);
    }
    pokemon = new Pokemon(dexId, this.user.id, response.id, 1, response.stats[5].base_stat, response.stats[4].base_stat,
      response.stats[3].base_stat, response.stats[0].base_stat, type1, type2,
      response.sprites['front_default'], response.sprites['back_default']);
    return pokemon;

  }

  catchPoke(poke) {
    this.user.counter = this.user.counter + 1;
    this.user.cTime = Date.now();
    if (!poke.backImg) {
      poke.backImg = ' ';
    }

    let pokeBox: Pokemon[];
    const boxSubscription = this.$box.subscribe(pokes => {
      pokeBox = pokes;
    });

    this.httpClient.post(`http://localhost:8080/PokeManiaAPI/api/pokemon`, poke, {
      withCredentials: true
    }).subscribe(
      data => {
        console.log('pokemon caught');
        this.userService.setUser(this.user);
        // console.log(data);
        poke.id = data;
        pokeBox.push(poke);
        this.boxStream.next(pokeBox); // send data to stream so components will update
        boxSubscription.unsubscribe(); // subscription no longer need
      },
      err => {
        console.log(err);
      }
    );

    this.httpClient.put(`http://localhost:8080/PokeManiaAPI/api/updatecounter`, this.user, {
      withCredentials: true
    }).subscribe(
      data => {
        console.log('counter incremented');
        this.userService.setUser(this.user);
      },
      err => {
        console.log(err);
      }
    );

  }

  resetCounter(id) {
    this.user.counter = 0;
    this.httpClient.put(`http://localhost:8080/PokeManiaAPI/api/updatecounter`, this.user, {
      withCredentials: true
    }).subscribe(
      data => {
        console.log('counter reset');
        this.userService.setUser(this.user);
      },
      err => {
        console.log(err);
      }
    );
  }

  release(id) {
    let pokeBox: Pokemon[];
    const boxSubscription = this.$box.subscribe(pokes => {
      pokeBox = pokes;
    });
    boxSubscription.unsubscribe();
    this.httpClient.delete<Pokemon>(`http://localhost:8080/PokeManiaAPI/api/pokemon?pokemonId=${id}`, {
      withCredentials: true,
    })
      .subscribe(data => {
        let i = 0;
        pokeBox.forEach(element => {
          if (element.id === id) {
            pokeBox.splice(i, 1);
          }
          i++;
        });
        this.boxStream.next(pokeBox);
      }, err => {
        console.log(err);
      });
  }

}
