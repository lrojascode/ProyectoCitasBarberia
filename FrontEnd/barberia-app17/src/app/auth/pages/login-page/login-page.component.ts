import { Component, inject } from '@angular/core';
import { LoginFormComponent } from '../../ui/login-form/login-form.component';
import { AuthStore } from '../../store/auth.store';
import { AuthCredentials } from '../../models/auth-credentials';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [LoginFormComponent],
  templateUrl: './login-page.component.html',
})
export class LoginPageComponent {
  private readonly authStore = inject(AuthStore);

  public isLoading = this.authStore.loading;

  public async login({ username, password }: AuthCredentials) {
    await this.authStore.login({ username, password });
  }
}
