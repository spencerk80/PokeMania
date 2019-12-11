import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/login/services/user.service';
import { User } from 'src/app/models/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pokemon-component',
  templateUrl: './pokemon-component.component.html',
  styleUrls: ['./pokemon-component.component.css']
})
export class PokemonComponentComponent implements OnInit {

  user: User;
  userSubscription = this.userService.$user.subscribe(element => {
    this.user = element;
  });

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    if (!this.user) {
      this.router.navigateByUrl('/login');
    }
  }

}
