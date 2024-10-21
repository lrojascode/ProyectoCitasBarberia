import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { AuthCredentials } from '../models/auth-credentials';
import { LoginResponse } from '../models/login-response.interface';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly _http = inject(HttpClient);
  private readonly _urlBase = environment.url;

  public login(credentials: AuthCredentials) {
    const url = `${this._urlBase}/login`;
    return this._http.post<LoginResponse>(url, credentials);
  }
}
