import { Routes } from '@angular/router';
import { SiteLayoutComponent } from './ui/layouts/site-layout/site-layout.component';
import { HomeComponent } from './pages/home/home.component';
import { ProfesionalsComponent } from './pages/profesionals/profesionals.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { ProfesionalDetailComponent } from './ui/components/profesional-detail/profesional-detail.component';
import { ServicesByProfesionalComponent } from './ui/components/services-by-profesional/services-by-profesional.component';


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
      },
      {
        path: 'profesionals/:id',
        component: ProfesionalDetailComponent,
      },
      {
        path: 'profesionals/:id/services',
        component: ServicesByProfesionalComponent,
      },
      {
        path: '**',
        redirectTo: 'home',
      },
    ],
  },
];
