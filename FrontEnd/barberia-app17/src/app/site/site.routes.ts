import { RouterModule, Routes } from '@angular/router';
import { SiteLayoutComponent } from './ui/layouts/site-layout/site-layout.component';
import { HomeComponent } from './pages/home/home.component';
import { ProfesionalsComponent } from './pages/profesionals/profesionals.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { ProfesionalDetailComponent } from './ui/components/profesional-detail/profesional-detail.component';
import { ServicesByProfesionalComponent } from './ui/components/services-by-profesional/services-by-profesional.component';
import { isAuthenticatedGuard } from '../auth/guards/is-authenticated.guard';
import { ForgotPasswordComponent } from '../auth/ui/forgot-password/forgot-password.component';
import { NgModule } from '@angular/core';
import { routes } from '../app.routes';
import { ResetPasswordComponent } from '../auth/ui/reset-password/reset-password.component';

export const SITE_ROUTES: Routes = [
  {
    path: '',
    component: SiteLayoutComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent,
      },
      {
        path: 'profesionals',
        component: ProfesionalsComponent,
      },
      {
        path: 'perfil',
        component: PerfilComponent,
        canActivate: [isAuthenticatedGuard],
      },
      {
        path: 'profesionals/:id',
        component: ProfesionalDetailComponent,
        canActivate: [isAuthenticatedGuard],
      },
      {
        path: 'profesionals/:id/services',
        component: ServicesByProfesionalComponent,
        canActivate: [isAuthenticatedGuard],
      },
      {
        path: 'auth/forgot-password',
        component: ForgotPasswordComponent
      },
      {
        path: 'auth/reset-password',
        component: ResetPasswordComponent
      },
      {
        path: '**',
        redirectTo: 'home',
      },
    ],
  },
];
