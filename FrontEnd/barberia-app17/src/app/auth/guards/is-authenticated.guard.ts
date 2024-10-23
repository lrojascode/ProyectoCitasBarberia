import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthFacade } from '../services/auth-facade.service';
import { JwtService } from '../services/jwt.service';

export const isAuthenticatedGuard: CanActivateFn = (route, state) => {
  const authFacade = inject(AuthFacade);
  const jwtService = inject(JwtService);
  const router = inject(Router);

  const token = authFacade.token();

  if (!token || jwtService.isTokenExpired(token)) {
    return router.createUrlTree(['/auth/login'], {
      queryParams: {
        returnUrl: state.url,
      },
    });
  } else {
    return true;
  }
};
