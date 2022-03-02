import { Component, OnInit } from '@angular/core';
import { Login } from '../login';
import { WeatherService } from '../weather.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit { 
  login:Login=new Login();
  loginMessage:string="--";
  constructor(public service:WeatherService) { }

  ngOnInit(): void {
  }
  doLogin(fm:any){
    console.log('-------- '+this.login);
    this.service.doLogin(this.login);
    
  }
}
