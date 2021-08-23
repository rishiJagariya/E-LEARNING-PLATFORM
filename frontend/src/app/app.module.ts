import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { TrainerprofileComponent } from './trainerprofile/trainerprofile.component';
import { FormsModule } from '@angular/forms';
import { AddcourseComponent } from './addcourse/addcourse.component';
import { UpdatecourseComponent } from './updatecourse/updatecourse.component';
import { StudentprofileComponent } from './studentprofile/studentprofile.component';
import { CartComponent } from './cart/cart.component';
import { CoursesComponent } from './courses/courses.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
