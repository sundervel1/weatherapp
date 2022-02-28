import { Injectable } from '@angular/core';
import { Weather } from './weather';
import { HttpClient } from '@angular/common/http';
import { Login } from './login';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {
  loggedIn=false;
  message:string;
  userName:string;
  weather:Weather=new Weather();
  http:HttpClient;
  
  constructor(http:HttpClient) { 
    this.http=http;
  }
  doLogin(login: Login) {
    
    const header={'content-type': 'application/json'};
    const body=JSON.stringify(login);
    console.log('--'+body);
    this.http.post('http://localhost:8585/weather/login',body,{'headers':header,'responseType': 'text'})
       .subscribe(data=>{
         console.log("--data--"+data);
         login.message=data;
         this.loggedIn=true;
         this.userName=login.username;
         sessionStorage.setItem("username",login.username);
       }, error=>{
          console.log('error from rest call ');
          login.message="Error during login";
       }
       );
  }
  logOut(login:Login) {
    console.log("session username: "+sessionStorage.getItem("username"));
    sessionStorage.clear();
    this.loggedIn=false;
    login.message="You have logged out of the system";
    // const header={'content-type': 'application/json'};
    // const body=JSON.stringify(login);
    // console.log('...-'+body);
    // this.http.post('http://localhost:8585/weather/logout',body,{'headers':header,'responseType': 'text'})
    //    .subscribe(data=>{
    //      console.log("..--data--"+data);
    //      login.message=data;
    //      this.loggedIn=false;
    //      this.userName="";
    //    }, error=>{
    //       console.log('error from rest call ');
    //       login.message="Error during logout";
    //    }
    //    );
  }

  fetchWeather(wthr:Weather){    
    this.http.get('http://api.openweathermap.org/data/2.5/weather?q='+wthr.name+','+wthr.country+'&appid='+wthr.apikey)
        .subscribe(
          data=>{
            this.convert(data);
          }
        );
  }
  convert(data:any){
    this.weather.lat=data.coord.lat;
    this.weather.lon=data.coord.lon;
    this.weather.main=data.weather[0].main;
    this.weather.name=data.name;
    this.weather.country=data.sys.country;
    //console.log("** "+this.weather.lat);
  }
  getWeather(){
    return this.weather;
  }
}
