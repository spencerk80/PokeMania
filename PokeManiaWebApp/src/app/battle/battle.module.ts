import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BattleComponent } from './components/battle/battle.component';
import { DoBattleService } from './services/do-battle.service';
import { TeamfetchService } from './services/teamfetch.service';
import { TypeAdvCalculatorService } from './services/type-adv-calculator.service';
import { RouterModule } from '@angular/router';
import { FriendSelectComponent } from './components/friend-select/friend-select.component';
import { FriendserviceService } from '../friends/services/friendservice.service';
import { OpponentService } from './services/opponent.service';



@NgModule({
  declarations: [BattleComponent, FriendSelectComponent],
  imports: [
    CommonModule,
    RouterModule
  ],
  providers: [DoBattleService, TeamfetchService, TypeAdvCalculatorService, FriendserviceService, OpponentService],
  bootstrap: [FriendSelectComponent]
})
export class BattleModule { }
