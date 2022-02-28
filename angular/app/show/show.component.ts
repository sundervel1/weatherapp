import { Component, OnInit } from '@angular/core';
import { Weather } from '../weather';
import { WeatherService } from '../weather.service';

@Component({
  selector: 'app-show',
  templateUrl: './show.component.html',
  styleUrls: ['./show.component.css']
})
export class ShowComponent implements OnInit {
  weather:Weather=new Weather();
  savedFav=false;
  constructor(public service:WeatherService) { }

  ngOnInit(): void {
    
  }
  getWeather(fm:any){
    this.service.fetchWeather(this.weather);
    this.weather = this.service.getWeather();
    console.log(this.weather);
  }
  addFavourite(){
    this.service.addFavourite(this.weather);
    this.savedFav=true;
  }

}
