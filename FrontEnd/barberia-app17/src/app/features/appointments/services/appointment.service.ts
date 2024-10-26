import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AppointmentService {
  private readonly _http = inject(HttpClient);
  private readonly _urlBase = `${environment.apiUrl}/citas`;

  getAvailableTimes(empleadoId: number, fecha: string): Observable<any> {
    return this._http.get(
      `${this._urlBase}/${empleadoId}/disponibilidad?fecha=${fecha}`,
    );
  }
}
