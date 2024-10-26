// interfaces/citas.interfaces.ts
export interface Cita {
    id: number;
    customers_id: number;
    employees_id: number;
    services_id: number;
    datetime: string;
    end_time: string;
    cancelled: boolean;
    serviceName?: string;
  }
  
  export interface CitasResponse {
    citas: Cita[];
  }
  
  export interface Service {
    id: number;
    name: string;
    description: string;
    price: number;
    duration_minutes: number;
  }
  
  export interface ServiceResponse {
    fecha: string;
    services: Service;
    mensaje: string;
    status: string;
  }

  export interface Cita {
    id: number;
    customers_id: number;
    employees_id: number;
    services_id: number;
    datetime: string;
    end_time: string;
    cancelled: boolean;
    serviceName?: string;
    employeeName?: string;  // Nuevo campo
  }