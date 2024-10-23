import { inject, Injectable, Signal } from '@angular/core';
import { AuthStore } from '../store/auth.store';
import { AuthCredentials } from '../models/auth-credentials';

@Injectable({
  providedIn: 'root',
})
export class AuthFacade {
  private readonly authStore = inject(AuthStore);

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

  public login(credentials: AuthCredentials) {
    this.authStore.login(credentials);
  }
}
