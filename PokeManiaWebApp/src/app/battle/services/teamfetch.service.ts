import { Injectable } from '@angular/core';
import { Pokemon } from '../../models/Pokemon'
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TeamfetchService {

  constructor(private http: HttpClient) { }

  //Fetch a team for a user
  public async fetchTeam(userID: number): Promise<Pokemon[]> {

    let response: any

    if(userID == -2)

      return await this.genNPCTeam(userID)

    try {

      response = await this.http.get(`http://localhost:8080/PokeManiaAPI/api/pokemonteam?userId=${userID}`, {withCredentials: true}).toPromise()

    } catch(err) {

      console.error(err)

    }

    return <Pokemon[]> response

  }

  private async genNPCTeam(userID: number): Promise<Pokemon[]> {

    let team: Pokemon[] = []
    let pokeID: number = Math.floor(Math.random() * 700 + 1)

    for(let i = 0; i < 6; i++)

      team.push(await this.getPokemon(pokeID += 5, userID))

    return team

  }

  private async getPokemon(id: number, userID: number): Promise<Pokemon> {
    
    let pokemon: Pokemon
    let response: any //Has to be for the pokemon constructor
    let type1: string
    let type2: string

    try {
      
      response = await this.http.get(`https://pokeapi.co/api/v2/pokemon/${id}`).toPromise()

    } catch(err) {

      console.error(err)
      return

    }

    if(response.types[0].slot == 1) {
    
      type1 = response.types[0].type.name
      type1 = type1.charAt(0).toUpperCase() + type1.substring(1)

    }
      
    else {

      type1 = response.types[1].type.name
      type2 = response.types[0].type.name

      type1 = type1.charAt(0).toUpperCase() + type1.substring(1)
      type2 = type2.charAt(0).toUpperCase() + type2.substring(1)

    }

    pokemon = new Pokemon(null, userID, response.id, 1, response.stats[5].base_stat, response.stats[4].base_stat,
                          response.stats[3].base_stat, response.stats[0].base_stat, type1, type2, 
                          response.sprites['front_default'], response.sprites['back_default'])

    return pokemon

  }

}
