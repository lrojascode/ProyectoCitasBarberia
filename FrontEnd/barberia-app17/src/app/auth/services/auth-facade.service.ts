import { inject, Injectable, Signal } from '@angular/core';
import { AuthStore } from '../store/auth.store';
import { AuthService } from './auth.service';
import { AuthCredentials } from '../models/auth-credentials';
import { Observable } from 'rxjs';
import { LoginResponse } from '../models/login-response.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthFacade {
  private readonly authStore = inject(AuthStore);
  private readonly authService = inject(AuthService);

  get loading(): Signal<boolean> {
    return this.authStore.loading;
  }

  get error(): Signal<string | null> {
    return this.authStore.error;
  }

  get token(): Signal<string | undefined> {
    return this.authStore.token;
  }

  get username(): Signal<string | undefined> {
    return this.authStore.username;
  }

  public login(credentials: AuthCredentials): Observable<LoginResponse> {
    return this.authService.login(credentials);
  }
}
