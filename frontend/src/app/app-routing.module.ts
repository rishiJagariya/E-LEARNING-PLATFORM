import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './cart/cart.component';
import { CoursesComponent } from './courses/courses.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';


const routes: Routes = [
  { path: 'register', component:RegisterComponent  },
  { path: 'login', component:LoginComponent},
  { path: 'forgotpassword', component:ForgotpasswordComponent},
  { path: 'header', component:HeaderComponent},
  { path: 'course', component:CoursesComponent},
  { path: 'cart', component:CartComponent}
  //add a path for home page
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [
  RegisterComponent, LoginComponent,ForgotpasswordComponent
];
