import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/components/login/login.component';
import { LoginModule } from './login/login.module';
import { BattleComponent } from './battle/components/battle/battle.component';
import { BattleModule } from './battle/battle.module';
import { PokemonModule } from './pokemon/pokemon.module';
import { PokemonComponentComponent } from './pokemon/components/pokemon-component/pokemon-component.component';
import { FriendsComponent } from './friends/components/friends/friends.component';
import { FriendsModule } from './friends/friends.module';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { RegisterComponent } from './login/components/register/register.component';
import { FriendSelectComponent } from './battle/components/friend-select/friend-select.component';


const routes: Routes = [
  {
    path: '',
    component: LoginComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'prepare%20for%20battle',
    component: FriendSelectComponent
  },
  {
    path: 'battle',
    component: BattleComponent
  },
  {
    path: 'poke',
    component: PokemonComponentComponent
  },
  {
    path: 'friends',
    component: FriendsComponent
  },

  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    LoginModule,
    BattleModule,
    PokemonModule,
    FriendsModule
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
