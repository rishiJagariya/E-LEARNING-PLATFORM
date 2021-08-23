import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ForgotpasswordComponent } from './forgotpassword/forgotpassword.component';
import { TrainerprofileComponent } from './trainerprofile/trainerprofile.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    ForgotpasswordComponent,
    TrainerprofileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
