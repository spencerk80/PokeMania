import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { UserService } from './services/user.service';
import { FormsModule } from '@angular/forms'
import { HttpClient, HttpClientModule } from '@angular/common/http'
import { RouterModule } from '@angular/router';
import { RegisterComponent } from './components/register/register.component';



@NgModule({
  providers: [UserService, HttpClient],
  declarations: [LoginComponent, RegisterComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    RouterModule
  ],
  bootstrap: [LoginComponent]
})
export class LoginModule { }
