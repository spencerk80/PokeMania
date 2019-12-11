import { Injectable } from '@angular/core';
import { User } from '../../models/User';
import { Observable, of, ReplaySubject} from 'rxjs';

@Injectable()
export class UserService {

  private _user: User = null
  private userStream = new ReplaySubject<User>(1)
  public $user  = this.userStream.asObservable()

  constructor() { }

  public setUser(user: User) {

    this.userStream.next(user)

  }

}
