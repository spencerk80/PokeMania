import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BattleTurn } from 'src/app/models/BattleTurn';
import { Pokemon } from 'src/app/models/Pokemon';
import { TypeAdvCalculatorService } from './type-adv-calculator.service';

@Injectable({
  providedIn: 'root'
})
export class DoBattleService {

  private _results: BattleTurn[] = []
  private _team1: Pokemon[] = []
  private _team2: Pokemon[] = []
  private _winner: number = null
  private _loser: number = null
  private _uID1: number
  private _uID2: number

  constructor(private http: HttpClient, private typeAdvCalc: TypeAdvCalculatorService) { }

  set team1(team: Pokemon[]) { this._team1 = team.reverse() }
  set team2(team: Pokemon[]) { this._team2 = team.reverse() }
  get winner() { return this._winner }
  get loser() { return this._loser }

  public async executeBattle(): Promise<BattleTurn[]> {

    let pokemon1: Pokemon = null
    let pokemon2: Pokemon = null
    let mod1: number = 0
    let mod2: number = 0
    let hp1: number = 0
    let hp2: number = 0
    this._uID1 = this._team1[0].trainerId
    this._uID2 = this._team2[0].trainerId

    //While there are pokemon who can fight
    while(this._team1.length > 0 && this._team2.length > 0) {

      //Get the next pokemon
      if(pokemon1 == null) {

        pokemon1 = this._team1.pop()
        hp1 = pokemon1.hp

      }

      if(pokemon2 == null) { 
        
        pokemon2 = this._team2.pop()
        hp2 = pokemon2.hp

      }

      //Calculate the type adv modifier
      mod1 = this.typeAdvCalc.modifierCalc(pokemon1, pokemon2)
      mod2 = this.typeAdvCalc.modifierCalc(pokemon2, pokemon1)

      //Who goes first?
      let p1GoesFirst = pokemon1.spd >= pokemon2.spd

      //While both fighting pokemon haven't fainted
      while(hp1 > 0 && hp2 > 0) {

        let dmg: number

        if(p1GoesFirst) {

          dmg = this.damage(pokemon1, pokemon2, mod1)
          
          hp2 -= dmg
          p1GoesFirst = false
          this._results.push(new BattleTurn(pokemon1, pokemon2, dmg))

        } else {

          dmg = this.damage(pokemon2, pokemon1, mod2)
          
          hp1 -= dmg
          p1GoesFirst = true
          this._results.push(new BattleTurn(pokemon2, pokemon1, dmg))

        }

      }

      if(hp1 <= 0) pokemon1 = null
      if(hp2 <= 0) pokemon2 = null

    }

    if(hp1 > 0) {

      this._winner = this._uID1
      this._loser = this._uID2

    } else {

      this._winner = this._uID2
      this._loser = this._uID1

    }

    return this._results

  }

  private damage(atkr: Pokemon, dfndr: Pokemon, typeAdv: number): number {

    const MOVE_POWER = 60

    //The real formula from the games
    return Math.floor(((((2 * atkr.level) / 5 + 2) * MOVE_POWER * (atkr.att / dfndr.def)) / 50 + 10) * typeAdv)

  }

}
