import { Component, OnInit } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-atmslist',
  templateUrl: './atmslist.component.html',
  styleUrls: ['./atmslist.component.css']
})
export class AtmslistComponent extends BaseListCtl implements OnInit {

  public form = {
    error: false,
    message: null,
    preload: [],
    data: { id: null },
    inputerror: {
      cashAvailable: null
    },
    searchParams: {
      cashAvailable: '',
      lastRestockedDate: '',
      locationId: ''
    },
    searchMessage: null,
    list: [],
    pageNo: 0
  };

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.ATMS, locator, route);
  }

  ngOnInit() {
    super.ngOnInit();
  }

  formatDate(event: any) {
    const selectedDate = new Date(event);
    const formattedDate = selectedDate.toISOString().split('T')[0];
    this.form.searchParams.lastRestockedDate = formattedDate;
  }

  convertToLocalDate(dateString: string): string {
    const date = new Date(dateString);
    const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return date.toLocaleDateString(undefined, options);
  }

  validateCashInput(event: any) {
    const value = event.target.value.trim();
    const isValidNumber = /^[0-9]+(\.[0-9]+)?$/.test(value);
    const floatValue = parseFloat(value);

    if (value === '') {
      this.form.inputerror.cashAvailable = null; // Clear the error message
    } else if (!isValidNumber || floatValue > 1000000000 || !value.includes('.')) {
      this.form.inputerror.cashAvailable = 'Please type an exact cash available value (e.g., 90.9, 1000000.5)';
    } else {
      this.form.inputerror.cashAvailable = null;
      this.form.searchParams.cashAvailable = floatValue.toString();
    }
  }

  submit() {
    this.form.pageNo = 0;
    this.search();
  }

  search() {
    this.form.searchMessage = null;
    super.search();
  }
}
