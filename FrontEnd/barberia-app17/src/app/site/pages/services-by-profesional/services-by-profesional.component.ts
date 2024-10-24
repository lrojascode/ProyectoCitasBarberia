import {
  afterNextRender,
  Component,
  inject,
  Input,
  numberAttribute,
  OnInit,
} from '@angular/core';
import { ProfesionalsService } from '../../../features/profesionals/services/profesionals.service';
import { Observable } from 'rxjs';
import { patchState, signalState } from '@ngrx/signals';
import { Service } from '../../../features/barber-services/models/service.interface';

@Component({
  selector: 'app-services-by-profesional',
  standalone: true,
  imports: [],
  templateUrl: './services-by-profesional.component.html',
  styles: ``,
})
export class ServicesByProfesionalComponent implements OnInit {
  private readonly profesionalsService = inject(ProfesionalsService);

  @Input({ transform: numberAttribute }) id = 0;

  public profesionalServices = signalState<{
    employee: string;
    services: Service[];
  }>({
    employee: '',
    services: [],
  });

  ngOnInit() {
    this.profesionalsService.getServicesByEmployee(this.id).subscribe((res) => {
      patchState(this.profesionalServices, res);
    });
  }
}
