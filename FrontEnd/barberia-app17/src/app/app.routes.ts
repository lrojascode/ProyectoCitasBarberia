import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./dashboard/dashboard.routes').then((c) => c.DASHBOARD_ROUTES),
  },
  {
    path: '',
    loadChildren: () => import('./site/site.routes').then((r) => r.SITE_ROUTES),
  },
  {
    path: '**',
    redirectTo: 'dashboard',
  },
];
