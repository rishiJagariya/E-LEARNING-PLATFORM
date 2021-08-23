import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddcourseComponent } from './addcourse/addcourse.component';
import { CartComponent } from './cart/cart.component';
import { CoursesComponent } from './courses/courses.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { StudentprofileComponent } from './studentprofile/studentprofile.component';
import { TrainerprofileComponent } from './trainerprofile/trainerprofile.component';
import { UpdatecourseComponent } from './updatecourse/updatecourse.component';


const routes: Routes = [
  { path: 'register', component:RegisterComponent  },
  { path: 'login', component:LoginComponent},
  { path: 'forgotpassword', component:ForgotpasswordComponent},
  { path: 'trainerprofile', component:TrainerprofileComponent},
  { path: 'addcourse', component:AddcourseComponent},
  { path: 'updatecourse', component:UpdatecourseComponent},
  { path: 'studentprofile', component:StudentprofileComponent},
  { path: 'cart', component:CartComponent},
  { path: 'courses', component:CoursesComponent},
  { path: 'header', component:HeaderComponent},
  
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
  TrainerprofileComponent, 
  AddcourseComponent, 
  UpdatecourseComponent,
  StudentprofileComponent,
  CartComponent,
  CoursesComponent,
  HeaderComponent
];
