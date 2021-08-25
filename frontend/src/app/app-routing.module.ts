import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddcourseComponent } from './addcourse/addcourse.component';
import { AuthGuard } from './auth.guard';
import { CartComponent } from './cart/cart.component';
import { CoursesComponent } from './courses/courses.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { StudentprofileComponent } from './studentprofile/studentprofile.component';
import { TrainerprofileComponent } from './trainerprofile/trainerprofile.component';
import { UpdatecourseComponent } from './updatecourse/updatecourse.component';


const routes: Routes = [
  { path: '', component: RegisterComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'login', component:LoginComponent},
  { path: 'forgotpassword', component:ForgotpasswordComponent},
  { 
    path: 'header', 
    component:HeaderComponent, 
    canActivate : [AuthGuard],
    data : {
      role : 'student'
    }
  },
  { 
    path: 'home', 
    component:HomeComponent,
    canActivate : [AuthGuard],
    data : {
      role : 'student'
    }
  },
  { 
    path : 'studentprofile', 
    component : StudentprofileComponent,
    canActivate : [AuthGuard],
    data : {
      role : 'student'
    }
  },
  { 
    path: 'trainerprofile', 
    component:TrainerprofileComponent,
    canActivate : [AuthGuard],
    data : {
      role : 'trainer'
    }
  },
  { 
    path: 'courses', 
    component:CoursesComponent,
    canActivate : [AuthGuard],
    data : {
      role : 'student'
    }
  },
  { 
    path: 'cart', 
    component:CartComponent,
    canActivate : [AuthGuard],
    data : {
      role : 'student'
    }
  },
  { 
    path: 'addcourse', 
    component:AddcourseComponent,
    data : {
      role : 'trainer'
    }
  },
  { 
    path: 'updatecourse', 
    component:UpdatecourseComponent,
    data : {
      role : 'trainer'
    }
  },
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
