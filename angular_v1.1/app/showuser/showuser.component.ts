import { Component, OnInit } from '@angular/core';
import { WeatherService } from '../weather.service';

@Component({
  selector: 'app-showuser',
  templateUrl: './showuser.component.html',
  styleUrls: ['./showuser.component.css']
})
export class ShowuserComponent implements OnInit {
  image:any;
  dbImage:string;
  constructor(public service:WeatherService) { }

  ngOnInit(): void {
  }
  viewImage(){
    this.service.viewImage(this.image);
  }
}
