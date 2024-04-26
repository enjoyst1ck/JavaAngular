import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  //{ path: '', component: StuffListComponent },
  //{ path: ':id', component: StuffDetailsComponent },
  //{ path: '/update', component: StuffFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StuffRoutingModule { }
