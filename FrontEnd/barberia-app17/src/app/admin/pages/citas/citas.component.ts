import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CitasService } from '../../services/citas.service';
import { forkJoin, Observable } from 'rxjs';
import { Cita, CitasResponse, ServiceResponse } from '../../interfaces/citas.interfaces';
import { Router } from '@angular/router';


@Component({
  selector: 'app-citas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './citas.component.html'
})
export class CitasComponent implements OnInit {
  citas: Cita[] = [];
  loading = false;
  error = false;
  errorMessage = '';

  constructor(private citasService: CitasService, private router: Router) {}

  ngOnInit() {
    this.loadCitas();
  }

  loadCitas() {
    this.loading = true;
    this.error = false;

    this.citasService.getCitas().subscribe({
      next: (response: CitasResponse) => {
        console.log('Respuesta del servidor:', response);
        const citasArray = response.citas || [];

        // Crear arrays de observables para servicios y empleados
        const serviceRequests: Observable<ServiceResponse>[] = citasArray.map(cita => 
          this.citasService.getServicio(cita.services_id)
        );
        
        const employeeRequests: Observable<any>[] = citasArray.map(cita =>
          this.citasService.getEmpleado(cita.employees_id)
        );

        // Combinar todas las peticiones
        if (citasArray.length > 0) {
          forkJoin({
            services: forkJoin(serviceRequests),
            employees: forkJoin(employeeRequests)
          }).subscribe({
            next: (responses) => {
              console.log('Respuestas combinadas:', responses);
              // Combinar la información de citas con servicios y empleados
              this.citas = citasArray.map((cita, index) => ({
                ...cita,
                serviceName: responses.services[index].services.name,
                employeeName: `${responses.employees[index].productos.firstName} ${responses.employees[index].productos.lastName}`
              }));
              console.log('Citas con detalles completos:', this.citas);
              this.loading = false;
            },
            error: (error: Error) => {
              console.error('Error al cargar los detalles:', error);
              this.error = true;
              this.errorMessage = 'Error al cargar los detalles de las citas';
              this.loading = false;
            }
          });
        } else {
          this.citas = citasArray;
          this.loading = false;
        }
      },
      error: (error: Error) => {
        console.error('Error al cargar citas:', error);
        this.error = true;
        this.errorMessage = 'Error al cargar las citas';
        this.loading = false;
      }
    });
  }

  openNewCitaForm(): void {
    console.log('Abrir formulario nueva cita');
  }

  editCita(cita: Cita): void {
    console.log('Editar cita:', cita);
  }

  deleteCita(id: number): void {
    if (confirm('¿Está seguro de que desea eliminar esta cita?')) {
      this.citasService.deleteCita(id).subscribe({
        next: () => {
          this.loadCitas();
        },
        error: (error: Error) => {
          console.error('Error eliminando cita:', error);
          this.errorMessage = 'Error al eliminar la cita';
          this.error = true;
        }
      });
    }
  }

  cerrarSesion(): void {
    // Eliminar el token del localStorage
    localStorage.removeItem('auth');
    
    // Redirigir al usuario a la página de inicio de sesión
    this.router.navigate(['/auth/login']); // Cambia '/login' por la ruta de tu componente de inicio de sesión
  }
}