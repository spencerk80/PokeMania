import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/login/services/user.service';
import { User } from '../../models/User'

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  private userSub = this.userService.$user.subscribe(user => this.user = user)
  private user: User

  constructor(private userService: UserService) { }

  ngOnInit() { setTimeout(() => {
    console.log(this.user)
  }, 2000);
  }

  ngOnDestroy() {

    this.userSub.unsubscribe()

  }

}
