import { Component, OnInit } from '@angular/core';
import { Login } from '../login';
import { WeatherService } from '../weather.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  login:Login=new Login();
  constructor(public service:WeatherService) { }

  ngOnInit(): void {
  }
  doLogout(){
    this.login.username=this.service.userName;
     this.service.logOut(this.login);
  }
}
