import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FavoritesComponent } from './favorites/favorites.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { ShowComponent } from './show/show.component';

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
    path:'show-weather', component:ShowComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
