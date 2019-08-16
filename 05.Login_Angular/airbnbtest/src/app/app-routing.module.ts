import { NgModule, Host } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { PropertyComponent } from './host/property/property.component';
import { EditHouseComponent } from './host/edit-house/edit-house.component';
import { ViewHouseComponent } from './host/view-house/view-house.component';
import { DashboardComponent } from './host/dashboard/dashboard.component';
import { HostComponent } from './host/host/host.component';
import { AddHouseComponent } from './host/add-house/add-house.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AuthGuardService } from './guard/auth-guard.service';


const routes: Routes = [
    {
        path: '',
        redirectTo: 'homepage',
        pathMatch: 'full'
    },
    {
        path: 'homepage',
        component: HomePageComponent,
        children: [
            {
                path: '',
                redirectTo: 'home',
                pathMatch: 'full'
            },
            {
                path: 'home',
                component: HomeComponent
            },
            {
                path: 'user',
                component: UserComponent
            },
            {
                path: 'auth/login',
                component: LoginComponent
            },
            {
                path: 'signup',
                component: RegisterComponent
            },
        ]
    },

    {
        path: 'host',
        component: HostComponent,
        canActivate:[AuthGuardService],
        children: [

            {
                path: 'dashboard',
                component: DashboardComponent
            },
            {
                path: 'property',
                component: PropertyComponent
            },
            {
                path: 'property/add',
                component: AddHouseComponent,
            },
            {
                path: 'room/:id',
                component: ViewHouseComponent
            },
            {
                path: 'property/edit/:id',
                component: EditHouseComponent
            },
            {
                path: 'property/preview',
                component: ViewHouseComponent,
            }
        ]
    },
    {
        path: 'host/property/preview',
        component: ViewHouseComponent,
    }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
