import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { AuthCredentials } from '../models/auth-credentials';
import { firstValueFrom, tap } from 'rxjs';
import { LoginResponse } from '../models/login-response.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly _http = inject(HttpClient);
  private readonly _urlBase = environment.urlBase;

  public login(credentials: AuthCredentials): Promise<LoginResponse> {
    const url = `${this._urlBase}/login`;
    const response$ = this._http
      .post<LoginResponse>(url, credentials)
      .pipe(tap((response) => console.log('Login Response', response)));

    return firstValueFrom(response$);
  }
}
