import { UserSession } from '../models/user.session';
import { patchState, signalStore, withMethods, withState } from '@ngrx/signals';
import {
  setLoaded,
  setLoading,
  withCallState,
  withStorageSync,
} from '@angular-architects/ngrx-toolkit';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { AuthCredentials } from '../models/auth-credentials';
import { Router } from '@angular/router';

interface AuthState {
  userSession: UserSession | undefined;
}

const initialAuthState: AuthState = {
  userSession: undefined,
};

export const AuthStore = signalStore(
  { providedIn: 'root' },
  withState(initialAuthState),
  withCallState(),
  withStorageSync({
    key: 'auth',
    select: (state) => ({ userSession: state.userSession }),
  }),
  withMethods(
    (state, authService = inject(AuthService), router = inject(Router)) => ({
      login: async (credentials: AuthCredentials) => {
        patchState(state, setLoading());
        const response = await authService.login(credentials);
        patchState(state, { userSession: response });
        patchState(state, setLoaded());

        await router.navigateByUrl('/');
      },
    }),
  ),
);
