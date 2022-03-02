import { Component, OnInit } from '@angular/core';
import { Weather } from '../weather';
import { WeatherService } from '../weather.service';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {
  weather:Weather[] = [];

  constructor(public service:WeatherService) { }

  ngOnInit(): void {
    console.log('>>... ' + this.weather);
    this.weather = [];
    this.service.getFavorites();
    this.weather = this.service.getFavList();
  }

}
