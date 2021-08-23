import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { StudentprofileComponent } from './studentprofile/studentprofile.component';
import { TrainerprofileComponent } from './trainerprofile/trainerprofile.component';


const routes: Routes = [
  { path: 'register', component:RegisterComponent  },
  { path: 'login', component:LoginComponent},
  { path: 'forgotpassword', component:ForgotpasswordComponent},
  { path: 'trainerprofile', component:TrainerprofileComponent},
  { path: 'studentprofile', component:StudentprofileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [
  RegisterComponent, LoginComponent,ForgotpasswordComponent,
  TrainerprofileComponent,StudentprofileComponent
];
