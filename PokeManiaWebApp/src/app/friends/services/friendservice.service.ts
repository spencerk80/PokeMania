import { Injectable } from '@angular/core';
import { ReplaySubject,Subject } from 'rxjs';
import { User } from '../../models/User';
import { HttpClient } from '@angular/common/http';
import { UserService } from 'src/app/login/services/user.service';


@Injectable({
  providedIn: 'root'
})
export class FriendserviceService {

  friendStream = new ReplaySubject<User[]>(1);
  $friends = this.friendStream.asObservable();


  friends: User[];
  user: User;

  userSubscription = this.userService.$user.subscribe(element => {
    this.user = element;
  });

  friendsSubscription = this.friendStream.subscribe(friend => {
    this.friends = friend;
  });

  constructor(private httpClient: HttpClient, private userService: UserService) {
    this.refreshList();
  }

  refreshList(){
    this.httpClient.get<User[]>(`http://localhost:8080/PokeManiaAPI/api/getfriends?userid=${this.user.id}`, {
    withCredentials: true
  })
  .subscribe(data => {
    if(!data){
      data = [];
    }
    this.friendStream.next(data);
  }, err => {
    console.log(err);
  });
  }


}

