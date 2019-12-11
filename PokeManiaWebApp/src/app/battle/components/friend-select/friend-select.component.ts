import { Component, OnInit } from '@angular/core';
import { FriendserviceService } from 'src/app/friends/services/friendservice.service';
import { OpponentService } from '../../services/opponent.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-friend-select',
  templateUrl: './friend-select.component.html',
  styleUrls: ['./friend-select.component.css']
})
export class FriendSelectComponent implements OnInit {

  friends = []
  friendStream

  constructor(private friendService: FriendserviceService, private opponentService: OpponentService, private router: Router) { }

  ngOnInit() {

    this.friendStream = this.friendService.$friends.subscribe(friends => this.friends = friends)

  }

  fight(opponentID: number) {

    this.opponentService.setOpponent(opponentID)
    this.router.navigateByUrl('/battle')

  }

  ngOnDestroy() {

    this.friendStream.unsubscribe()

  }

}
