import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddcourseComponent } from './addcourse/addcourse.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UpdatecourseComponent } from './updatecourse/updatecourse.component';


const routes: Routes = [
  { path: 'register', component:RegisterComponent  },
  { path: 'login', component:LoginComponent},
  { path: 'forgotpassword', component:ForgotpasswordComponent},
  { path: 'addcourse', component:AddcourseComponent},
  { path: 'updatecourse', component:UpdatecourseComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [
  RegisterComponent,
  LoginComponent,
  ForgotpasswordComponent,
  AddcourseComponent,
  UpdatecourseComponent
];
