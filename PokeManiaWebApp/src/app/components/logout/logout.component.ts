import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/login/services/user.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private userService: UserService, private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }

  async logout() {

    try {
      
      const response = await this.http.put('http://localhost:8080/PokeMania/api/logout', null, {withCredentials: true}).toPromise

    } catch(err) {

      console.error(err)

    }

    this.userService.setUser(null)
    this.router.navigateByUrl('')

  }

}
