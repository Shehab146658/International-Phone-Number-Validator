import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CustomerListComponent } from './employee/employee-list/customer-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'numbers', pathMatch: 'full' },
  { path: 'numbers', component: CustomerListComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
