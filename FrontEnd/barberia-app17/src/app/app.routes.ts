import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./dashboard/dashboard.routes').then((c) => c.DASHBOARD_ROUTES),
  },
  {
    path: '**',
    redirectTo: 'dashboard',
  },
];
