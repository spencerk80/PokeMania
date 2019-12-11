import { Injectable } from '@angular/core';
import { User } from '../../models/User'
import { ReplaySubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OpponentService {

  private userStream = new ReplaySubject<number>(1)
  public $opponent  = this.userStream.asObservable()

  constructor() { }

  setOpponent(opponent: number) {

    this.userStream.next(opponent)
    
  }

}
