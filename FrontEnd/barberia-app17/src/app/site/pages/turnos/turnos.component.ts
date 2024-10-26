import { Component, inject, OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { CitasService } from '../../../admin/services/citas.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { JwtService } from '../../../auth/services/jwt.service';
import { AuthFacade } from '../../../auth/services/auth-facade.service';

interface CitaData {
  id: number;
  datetime: string;
  service: string;
  end_time: string;
  cancelled: boolean;
  employee: string;
  customer: string;
}

interface DecodedToken {
  sub: string;
  exp: number;
  role: string;
  nombre: string;
}

@Component({
  selector: 'app-turnos',
  templateUrl: './turnos.component.html',
  styleUrls: ['./turnos.component.css'],
  standalone: true,
  imports: [CommonModule, RouterModule],
})
export class TurnosComponent implements OnInit {
  private readonly authFacade = inject(AuthFacade);

  citas: CitaData[] = [];
  userEmail = '';
  citaId: number | null = null;
  userId = this.authFacade.userId;

  constructor(
    private turnosService: CitasService,
    private jwtService: JwtService,
  ) {}

  ngOnInit(): void {
    this.obtenerInfoUsuario();
  }

  obtenerInfoUsuario(): void {
    const storedToken = localStorage.getItem('auth');

    if (storedToken) {
      const parsedToken = JSON.parse(storedToken);
      const token = parsedToken.token;

      try {
        const decodedToken = this.jwtService.decodeToken(token) as DecodedToken;
        this.userEmail = decodedToken.sub;
        this.cargarCitas();
      } catch (error) {
        console.error('Error al decodificar el token:', error);
      }
    }
  }

  async cargarCitasPorCustomer() {
    const citas = await this.turnosService.getCitaByCustomer();
  }

  /*cargarCitas(): void {
    const storedToken = localStorage.getItem('auth');

    if (storedToken) {
      const parsedToken = JSON.parse(storedToken);
      const token = parsedToken.token;
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

      // Primer llamado para obtener todas las citas y extraer el ID
      this.turnosService.getAllCitas(headers).subscribe({
        next: (response) => {
          console.log('Respuesta inicial:', response);
          if (response.citas && response.citas.length > 0) {
            // Guardamos el ID de la primera cita que encontremos
            this.citaId = response.citas[0].id;
            console.log('ID encontrado:', this.citaId);

            // Ahora hacemos el llamado específico con ese ID
            if (this.citaId) {
              this.turnosService.getCitaById(this.citaId, headers).subscribe({
                next: (detailResponse) => {
                  console.log('Respuesta detallada:', detailResponse);
                  if (detailResponse.cita) {
                    // Guardamos la cita en el array
                    this.citas = [detailResponse.cita];
                    console.log('Citas actualizadas:', this.citas);
                  }
                },
                error: (error) => {
                  console.error('Error al obtener detalle de cita:', error);
                },
              });
            }
          }
        },
        error: (error) => {
          console.error('Error al cargar las citas:', error);
        },
      });
    }
  }*/

  cancelarCita(citaId: number): void {
    const storedToken = localStorage.getItem('auth');

    if (storedToken) {
      const parsedToken = JSON.parse(storedToken);
      const token = parsedToken.token;
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

      this.turnosService.cancelarCita(citaId, headers).subscribe({
        next: () => {
          alert('Cita cancelada exitosamente');
          // this.cargarCitas();
        },
        error: (error) => {
          console.error('Error en la cancelación:', error);
          alert('Error al cancelar la cita');
        },
      });
    }
  }

  formatearFecha(fecha: string): string {
    return new Date(fecha).toLocaleString('es-ES', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
    });
  }
}
