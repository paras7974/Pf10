import { Component, OnInit } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-ownerlist',
  templateUrl: './ownerlist.component.html',
  styleUrls: ['./ownerlist.component.css']
})
export class OwnerlistComponent extends BaseListCtl implements OnInit {

  public form = {
    error: false,
    message: null,
    preload: [],
    data: { id: null },
    inputerror: {},
    searchParams: {
      name: '',
      insuranceAmount: '',
      dob: '', // Initialize date field
      vehicleIdId: '',
      date: ''
    },
    searchMessage: null,
    list: [],
    pageNo: 0
  };

  isValidMobileInput: boolean = true;
  isValidNameInput: boolean = true;
  nameErrorMessage: string = '';
  mobileErrorMessage: string = '';
  insuranceAmountErrorMessage: string = '';

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.OWNER, locator, route);
  }

  ngOnInit() {
    super.ngOnInit();
  }

  // Format date function
  formatDate(event: any) {
    const selectedDate = new Date(event);
    const formattedDate = selectedDate.toISOString().split('T')[0]; // Ensure it's in ISO format
    this.form.searchParams.date = formattedDate;
  }

  // Convert date to local format for display
  convertToLocalDate(dateString: string): string {
    const date = new Date(dateString);
    // Format date to 'MM/DD/YYYY'
    const options: any = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return date.toLocaleDateString(undefined, options);
  }

  // Validate input for name, mobile, and insuranceAmount fields
  validateInput(event: any, field: string) {
    const value = event.target.value;
    if (field === 'mobile') {
      this.isValidMobileInput = /^[0-9]*$/.test(value); // Check if the input contains only numbers
      if (!this.isValidMobileInput) {
        this.mobileErrorMessage = 'Please type numbers only';
      } else {
        this.mobileErrorMessage = '';
      }
    } else if (field === 'name') {
      this.isValidNameInput = /^[A-Za-z\s]*$/.test(value); // Check if the input contains only letters and spaces
      if (!this.isValidNameInput) {
        this.nameErrorMessage = 'Please type alphabets only';
      } else {
        this.nameErrorMessage = '';
      }
    } else if (field === 'insuranceAmount') {
      const amount = parseFloat(value);
      if (isNaN(amount) || amount < 10000 || amount > 500000) {
        this.insuranceAmountErrorMessage = 'Please type a value between 10000 to 500000';
      } else {
        this.insuranceAmountErrorMessage = '';
      }
    }
  }

  // Submit method
  submit() {
    // Reset page number before searching
    this.form.pageNo = 0;
    // Call the search method after formatting the date
    this.search();
  }

  // Search method
  search() {
    // Clear previous search message
    this.form.searchMessage = null;
    // Perform the search operation with the search parameters
    super.search();
  }
}
