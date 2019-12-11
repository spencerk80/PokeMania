import { Component, OnInit } from '@angular/core';
import { TeamfetchService } from '../../services/teamfetch.service';
import { DoBattleService } from '../../services/do-battle.service';
import { Pokemon } from 'src/app/models/Pokemon';
import { BattleTurn } from 'src/app/models/BattleTurn';
import { UserService } from 'src/app/login/services/user.service';
import { User } from '../../../models/User'
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { OpponentService } from '../../services/opponent.service';

@Component({
  selector: 'app-battle',
  templateUrl: './battle.component.html',
  styleUrls: ['./battle.component.css']
})
export class BattleComponent implements OnInit {

  private _team1: Pokemon[]
  private _team2: Pokemon[]
  public pokemon1: Pokemon
  public pokemon2: Pokemon
  public isDataReady: boolean = false
  public opponentDmg: number = 0
  public trainerDmg: number = 0
  public trainerHP: number = 0
  public opponentHP: number = 0
  public showOpponentDmg: boolean
  public showTrainerDmg: boolean
  private _battleTurns: BattleTurn[]
  private userSub = this.userService.$user.subscribe(user => this.user = user)
  private oppSub = this.opponentService.$opponent.subscribe(opp => this.oppID = opp)
  private user: User
  private oppID: number

  constructor(private teamFetcher: TeamfetchService, private battleCalc: DoBattleService, private userService: UserService, private router: Router,
            private http: HttpClient, private opponentService: OpponentService) { }

  ngOnInit() {
    this.run()
  }

  public run() {

    if(this.user == null) {
      
      this.router.navigateByUrl('')
      return

    }

    this.teamFetcher.fetchTeam(this.user.id).then(team => {

      this._team1 = team
      
      this.teamFetcher.fetchTeam(this.oppID).then(team => {

        this._team2 = team

        this.getBattleResults().then(turns => {

          this._battleTurns = turns

          if(this.battleCalc.winner == this.user.id) {

            this.user.wins++
            this.updateRecords(this.user)

          } else {

            this.user.losses++
            this.updateRecords(this.user)

          }

          if(turns[0].attacker.trainerId == this.user.id) {

            this.pokemon1 = turns[0].attacker
            this.pokemon2 = turns[0].defender

          } else {

            this.pokemon1 = turns[0].defender
            this.pokemon2 = turns[0].attacker

          }
          this.trainerHP = this.pokemon1.hp
          this.opponentHP = this.pokemon2.hp
          this.isDataReady = true
          this.displayResults()

        })

      })

    })

  }

  private async getBattleResults() {

    this.battleCalc.team1 = this._team1
    this.battleCalc.team2 = this._team2

    return await this.battleCalc.executeBattle()
    
  }

  private updateRecords(user: User): void {

    if(user.id == -2)

      return

    this.http.put(`http://localhost:8080/PokeManiaAPI/api/updateuser`, JSON.stringify(user), {withCredentials: true})
    .subscribe(response => {})

  }

  private displayResults() {

    let time = 0

    for(let turn of this._battleTurns) {

      setTimeout(() => this.updateScreen(turn.attacker, turn.defender, turn.attacker.trainerId === this.user.id, turn.damage), time += 1000);

    }

    setTimeout(() => {

      let dead = this._battleTurns[this._battleTurns.length - 1].defender

      if(dead == this.pokemon1)

        this.pokemon1.backImg = 'img'

      else

        this.pokemon2.frontImg = 'img'

    }, time += 1000)

  }

  private updateScreen(attacker: Pokemon, defender: Pokemon, trainerDidAttack: boolean, dmg: number) {

    if(trainerDidAttack) {

      if(this.pokemon1 != attacker) { 
        
        this.pokemon1 = attacker
        this.trainerHP = this.pokemon1.hp

      }

      if(this.pokemon2 != defender) {
        
        this.pokemon2 = defender
        this.opponentHP = this.pokemon2.hp

      }

      this.opponentDmg = dmg
      this.opponentHP -= dmg
      this.showOpponentDmg = true
      setTimeout(() => {this.showOpponentDmg = false}, 500);

    } else {

      if(this.pokemon1 != defender) {
        
        this.pokemon1 = defender
        this.trainerHP = this.pokemon1.hp

      }

      if(this.pokemon2 != attacker) {
        
        this.pokemon2 = attacker
        this.opponentHP = this.pokemon2.hp

      }

      this.trainerDmg = dmg
      this.trainerHP -= dmg
      this.showTrainerDmg = true
      setTimeout(() => {this.showTrainerDmg = false}, 500);

    }

  }

  ngOnDestroy() {
    this.userSub.unsubscribe()
    this.oppSub.unsubscribe()
  }

}
