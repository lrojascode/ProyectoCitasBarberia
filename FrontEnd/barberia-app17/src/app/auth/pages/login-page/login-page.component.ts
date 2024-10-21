import { Component, effect, inject } from '@angular/core';
import { LoginFormComponent } from '../../ui/login-form/login-form.component';
import { AuthStore } from '../../store/auth.store';
import { AuthCredentials } from '../../models/auth-credentials';
import { ToastService } from '../../../shared/services/toast.service';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [LoginFormComponent],
  templateUrl: './login-page.component.html',
})
export class LoginPageComponent {
  private readonly toastService = inject(ToastService);
  private readonly authStore = inject(AuthStore);

  public isLoading = this.authStore.loading;
  public error = this.authStore.error;

  constructor() {
    effect(() => {
      const error = this.error();
      if (error && error.trim() !== '') {
        this.toastService.showToast({
          severity: 'error',
          summary: 'Error',
          detail: this.error() ?? undefined,
        });
      }
    });
  }

  public login({ username, password }: AuthCredentials) {
    this.authStore.login({ username, password });
  }
}
