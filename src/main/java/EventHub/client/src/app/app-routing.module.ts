import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './modules/shared/home-page/home-page.component';
import { AuthGuard } from './modules/auth/auth.guard';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'events', loadChildren: () => import('./modules/event/event.module').then(m => m.EventModule), canActivate: [AuthGuard], data: {expectedRole: 'ROLE_ORGANIZER'} },
  { path: 'artists', loadChildren: () => import('./modules/artist/artist.module').then(m => m.ArtistModule) },
  { path: 'staff', loadChildren: () => import('./modules/staff/staff.module').then(m => m.StuffModule) },
  { path: 'reviews', loadChildren: () => import('./modules/review/review.module').then(m => m.ReviewModule) },
  { path: '', loadChildren: () => import('./modules/auth/auth.module').then(m => m.AuthModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
