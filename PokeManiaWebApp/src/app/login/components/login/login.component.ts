import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../../models/User'
import { HttpClient } from '@angular/common/http'
import { LoginForm } from 'src/app/models/LoginForm';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username: string = ''
  public password: string = ''
  public errorExists: boolean = false
  public errorMsg: string = ''

  constructor(private userService: UserService, private http: HttpClient, private router: Router) { }

  ngOnInit() {
  }

  login() {

    let user: User

    if(!this.checkErrors()) return

    this.getUser().then(user => {

      if(user !== undefined) {
      
        this.userService.setUser(user);
        this.router.navigateByUrl('/poke')

      } else this.displayError('Invalid login')

    })

  }

  async getUser(): Promise<User> {

    let loginInfo: LoginForm = new LoginForm(this.username, this.password)
    let response
    
    try {
    
      response = await this.http.post('http://localhost:8080/PokeManiaAPI/api/login', JSON.stringify(loginInfo), {withCredentials: true}).toPromise()

    } catch(err) {

      console.error(err)

    }

    console.log(response)
    
    return <User> response

  }

  checkErrors() {

    if(this.username === '') {

      this.displayError('Username must be provided!')
      return false

    }

    if(this.password === '') {

      this.displayError('Password must be provided!')
      return false

    }

    return true

  }

  displayError(msg: string) {

    this.errorExists = true
    this.errorMsg = msg

    setTimeout(() => {

      this.errorExists = false
      this.errorMsg = ''

    }, 3000);

  }

}
