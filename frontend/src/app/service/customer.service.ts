import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs/index";
import { ApiResponse } from '../model/api.response';
import { Employee } from '../model/customer.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class CustomerService {

  constructor(private http: HttpClient) { }
  private baseUrl: string = environment.baseUrl+'/api/jumia/numbers/get-all-customers';

  

  getPhoneNumbers() : Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }
}