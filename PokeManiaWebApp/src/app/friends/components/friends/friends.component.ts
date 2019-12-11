import { Component, OnInit } from '@angular/core';
import { FriendserviceService } from '../../services/friendservice.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { User } from '../../../models/User';
import { UserService } from 'src/app/login/services/user.service';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {

  friendsStream = this.friendService.$friends.subscribe(friendList => {
    this.friends = friendList
  })

  userStream = this.userService.$user.subscribe(user => {
    this.user = user;
  })

  user: User;
  friends = [];
  newFriend: string;
  firstname: string;
  lastname: string;
  username: string = null;
  wins: number;
  losses: number;

  constructor( private friendService: FriendserviceService, private httpClient: HttpClient, private userService:UserService) { }

  ngOnInit() {
  }

  findFriend(){
    this.httpClient.get<User>(`http://localhost:8080/PokeManiaAPI/api/finduser?username=${this.newFriend}`,{
      withCredentials: true
    })
    .subscribe(data => {
      if(data){
        console.log(data);
      
      for(let friend of this.friends){
        console.log("Data: "+data.username);
        console.log("Friend: "+friend.username);
        if(data.username == friend.username){
          return;
        }
        if(data.username == this.user.username){
          return;
        }
      }
        this.username = data.username;
        this.firstname = data.firstname;
        this.lastname = data.lastname;
        this.wins = data.wins;
        this.losses = data.losses;
      } else {
        this.username = null;
      }
    })
  }

  addFriend(){
    this.httpClient.post<User>(`http://localhost:8080/PokeManiaAPI/api/addfriend?friendusername=${this.username}`,
    JSON.stringify(this.user),
    {withCredentials: true}
    ).subscribe(response => {
      console.log(response);
    })
    this.newFriend = "";
    this.username = null;
    this.friendService.refreshList();
  }

  ngOnDestroy(){
    this.friendsStream.unsubscribe();
    this.userStream.unsubscribe();
  }

}
