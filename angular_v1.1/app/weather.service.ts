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
  weatherList:Weather[]=[];
  imagePostResponse:any;
  imageSuccessResponse:string;
  imageUrl:string;
  dbImage:any;
  constructor(http:HttpClient) { 
    this.http=http;
  }
  getImageUrl(): string {
    return this.imageUrl;
  }
  viewImage(image:any) {
    console.log('------ view image');
    this.http.get('http://localhost:8585/weather/files/'+image)
      .subscribe(
        res=>{
          console.log('..--**'+res);
          this.imagePostResponse = res;
          this.dbImage = 'data:image/jpeg;base64,' + this.imagePostResponse.image;
          console.log('*** dbImage:'+this.dbImage);
        }
        ,error=>{
          this.imageUrl = error.url.toString();
          console.log('error----'+this.imageUrl);
        }
      );
  }
  imageUploadAction(uplaodImage: File) {
    const imageFormData = new FormData();
    imageFormData.append('image',uplaodImage,uplaodImage.name);
    this.http.post('http://localhost:8585/weather/upload',imageFormData,{observe:'response'})
        .subscribe(
          response=>{
            if(response.status===200){
              this.imagePostResponse = response;
              this.imageSuccessResponse = this.imagePostResponse.body.message;
            }
            else{
              this.imageSuccessResponse = "Image not uploaded due to error";
            }
          }
        );
  }
  getFavorites() {
    this.weatherList = [];
    console.log('get fav..')
    this.http.get('http://localhost:8585/weather/getall/'+sessionStorage.getItem("username"))
       .subscribe(
         data=>{
           this.convertAll(data);
         }
       );
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
  register(login: any) {
      const header={'content-type': 'application/json'};
      const body=JSON.stringify(login);
      console.log('register--'+body);
      this.http.post('http://localhost:8585/weather/register',body,{'headers':header})
         .subscribe( data=>{
          console.log("data register : " + data);
          login.message="You have successfully registered with username: " + login.username;
          this.loggedIn=false;
          sessionStorage.clear();
         }, error=>{
           console.log('error in register call');
         }

         );
    }
  addFavourite(weather: Weather) {
    const header={'content-type': 'application/json'};
    const body=JSON.stringify(weather);
    console.log('--'+body);
    this.http.post('http://localhost:8585/weather/add',body,{'headers':header})
       .subscribe(
         data=>{
           console.log(data);
           let dataVal:any = Object.values(data);
           this.weatherList.push(dataVal);
          weather.message='Record stored in favourites for '+weather.username;
         }, error=>{
           console.log('error' + error)
         }
       );
  }
  logOut(login:Login) {
    console.log("session username: "+sessionStorage.getItem("username"));
    sessionStorage.clear();
    this.loggedIn=false;
    login.message="You have logged out of the system";
    this.weatherList = [];
    this.imageSuccessResponse ="";
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
    let name = sessionStorage.getItem("username");
    if(name!=null){
       this.weather.username= name;
    }
    //console.log("** "+this.weather.lat);
  }
  convertAll(data:any){
    console.log(data);
    //this.weatherList = [];
    for(let w of data) {
      let wthr = new Weather();
      wthr.username=w.username;
      wthr.name=w.name;
      wthr.main=w.main;
      wthr.country=w.country;
      wthr.lat=w.lat;
      wthr.lon=w.lon;
      this.weatherList.push(wthr);
    }
  }
  getWeather(){
    return this.weather;
  }
  getFavList(){
    return this.weatherList;
  }
}
