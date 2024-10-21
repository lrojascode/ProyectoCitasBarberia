import { UserSession } from '../models/user.session';
import { patchState, signalStore, withMethods, withState } from '@ngrx/signals';
import {
  setError,
  setLoaded,
  setLoading,
  withCallState,
  withStorageSync,
} from '@angular-architects/ngrx-toolkit';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { AuthCredentials } from '../models/auth-credentials';
import { Router } from '@angular/router';
import { rxMethod } from '@ngrx/signals/rxjs-interop';
import { pipe, switchMap, tap } from 'rxjs';
import { tapResponse } from '@ngrx/operators';
import { ToastService } from '../../shared/services/toast.service';
import { HttpErrorResponse } from '@angular/common/http';

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
    (
      state,
      authService = inject(AuthService),
      router = inject(Router),
      toastService = inject(ToastService),
    ) => ({
      login: rxMethod<AuthCredentials>(
        pipe(
          tap(() => patchState(state, setLoading())),
          switchMap((credentials) =>
            authService.login(credentials).pipe(
              tapResponse({
                next: (userSession) => {
                  patchState(state, { userSession });
                  router.navigateByUrl('/');
                },
                error: (err) => {
                  if (err instanceof HttpErrorResponse && err.status === 401) {
                    patchState(
                      state,
                      setLoaded(),
                      setError('Credenciales de acceso incorrectas'),
                    );
                  }
                  console.log(err);
                },
              }),
            ),
          ),
        ),
      ),
    }),
  ),
);
