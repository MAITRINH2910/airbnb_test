import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { UserComponent } from './user/user.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { httpInterceptorProviders } from './models/auth-interceptor';

import { HostComponent } from './host/host/host.component';
import { DashboardComponent } from './host/dashboard/dashboard.component';
import { PropertyComponent } from './host/property/property.component';
import { EditHouseComponent } from './host/edit-house/edit-house.component';
import { ViewHouseComponent } from './host/view-house/view-house.component';
import { AddHouseComponent } from './host/add-house/add-house.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AuthGuardService } from './guard/auth-guard.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    UserComponent,
    HostComponent,
    DashboardComponent,
    PropertyComponent,
    EditHouseComponent,
    ViewHouseComponent,
    AddHouseComponent,
    HomePageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
    
  ],
  providers: [httpInterceptorProviders, AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
