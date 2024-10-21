import { Authority } from './authority.interface';

export interface LoginResponse {
  email: string;
  authorities: Authority[];
  username: string;
  token: string;
}
