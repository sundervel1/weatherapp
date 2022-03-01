import { Component, OnInit } from '@angular/core';
import { Login } from '../login';
import { WeatherService } from '../weather.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  login:Login = new Login();
  constructor(public service:WeatherService) { }

  ngOnInit(): void {
  }
  register(fm:any){
     this.service.register(this.login);
  }
}
