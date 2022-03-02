import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FavoritesComponent } from './favorites/favorites.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { RegisterComponent } from './register/register.component';
import { ShowComponent } from './show/show.component';
import { ShowuserComponent } from './showuser/showuser.component';
import { UploadComponent } from './upload/upload.component';

const routes: Routes = [
  {
    path:'login', component:LoginComponent
  },
  {
    path:'logout', component:LogoutComponent
  },
  {
    path:'favorites', component:FavoritesComponent
  },
  {
    path:'register', component:RegisterComponent
  },
  {
    path:'upload', component:UploadComponent
  },
  {
    path:'showuser', component:ShowuserComponent
  },
  {
    path:'show-weather', component:ShowComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
