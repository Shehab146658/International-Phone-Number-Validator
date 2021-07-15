import { Component, OnInit, ViewChild } from '@angular/core';

import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { ApiResponse } from 'src/app/model/api.response';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  employees: Observable<ApiResponse>;
  //dtOptions: DataTables.Settings = {};
  //@ViewChild('dtOptions', {static: true}) table;

  constructor(private customerService: CustomerService,
    private router: Router) { 
      setTimeout(function(){
        $(function(){
          $('#example').DataTable();
      });
      },2000);
      
    }

  ngOnInit() {
    this.employees = this.customerService.getPhoneNumbers();
    setTimeout(function(){
      $(function(){
        $('#example').DataTable();
    });
    },2000);

  }


}
