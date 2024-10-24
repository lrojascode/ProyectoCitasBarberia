import {
  Component,
  Input,
  numberAttribute,
  OnInit,
  signal,
} from '@angular/core';
import { CalendarModule } from 'primeng/calendar';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { format } from 'date-fns';
import { es } from 'date-fns/locale';

@Component({
  selector: 'app-book-appointment',
  standalone: true,
  imports: [CalendarModule, FormsModule, DatePipe],
  templateUrl: './book-appointment.component.html',
  styles: ``,
})
export class BookAppointmentComponent implements OnInit {
  @Input({ transform: numberAttribute }) serviceId = 0;
  @Input({ transform: numberAttribute }) profesionalId = 0;

  date: Date | undefined;
  selectedDate = signal<Date>(new Date());
  minDate: Date | undefined;
  maxDate: Date | undefined;

  availableTimes: string[] = [
    '09:00',
    '10:00',
    '11:00',
    '12:00',
    '13:00',
    '14:00',
    '15:00',
    '16:00',
    '17:00',
    '18:00',
    '19:00',
    '20:00',
  ];

  ngOnInit() {
    const today = new Date();
    const month = today.getMonth();
    const year = today.getFullYear();
    const prevMonth = month === 0 ? 11 : month - 1;
    const prevYear = prevMonth === 11 ? year - 1 : year;
    const nextMonth = month === 11 ? 0 : month + 1;
    const nextYear = nextMonth === 0 ? year + 1 : year;
    this.minDate = new Date();
    // this.minDate.setMonth(prevMonth);
    // this.minDate.setFullYear(prevYear);
    this.maxDate = new Date();
    this.maxDate.setMonth(nextMonth);
    this.maxDate.setFullYear(nextYear);
  }

  protected readonly format = format;
  protected readonly es = es;
}
