import { Component, OnInit } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-routelist',
  templateUrl: './routelist.component.html',
  styleUrls: ['./routelist.component.css']
})
export class RoutelistComponent extends BaseListCtl implements OnInit {

  public form = {
    error: false,
    message: null,
    preload: {
      vehicleIdList: [] // Initialize customer list
    },
    data: { id: null ,
      allowedSpeed:''
    },
    inputerror: { quantity: '', productName: '' },
    searchParams: {
      name: '',
      date: '', // Initialize date field
      vehicleIdId: null, // Initialize vehicleIdId to null
      allowedSpeed: '',
      startDate: ''
    },
    searchMessage: null,
    list: [],
    pageNo: 0
  };

  isValidMobileInput: boolean = true;
  isValidNameInput: boolean = true;
  isValidAllowedSpeed: boolean = true;
  nameErrorMessage: string = '';
  mobileErrorMessage: string = '';
  allowedSpeedErrorMessage: string = '';

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.ROUTE, locator, route);
  }

  ngOnInit() {
    super.ngOnInit();
  }

  // Format date function
  formatDate(event: any) {
    const selectedDate = new Date(event);
    const formattedDate = selectedDate.toISOString().split('T')[0];
    this.form.searchParams.date = formattedDate;
  }

  // Convert date to local format for display
  convertToLocalDate(dateString: string): string {
    const date = new Date(dateString);
    const options: any = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return date.toLocaleDateString(undefined, options);
  }

  // Validate input for name, mobile, and allowedSpeed fields
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
    } else if (field === 'allowedSpeed') {
      if (value === '') {
        this.isValidAllowedSpeed = true; // Treat empty input as valid
        this.allowedSpeedErrorMessage = '';
      // } else {
      //   const speed = parseInt(value, 10);
      //   this.isValidAllowedSpeed = speed >= 70 && speed <= 150; // Check if the input is between 70 and 150
      //   if (!this.isValidAllowedSpeed) {
      //     this.allowedSpeedErrorMessage = 'Please type a valid allowed speed (70-150)';
      //   } else {
      //     this.allowedSpeedErrorMessage = '';
      //   }
      }
    }
  }
  validateNumericInput(event: KeyboardEvent) {
    const charCode = event.which || event.keyCode;
    const charStr = String.fromCharCode(charCode);

    // Regular expression to test if the character is a number
    const numberRegex = /^[0-9]+$/;

    // Test if the input matches the allowed characters and does not exceed the length
    if (!numberRegex.test(charStr) || this.form.data.allowedSpeed.length >= 3) {
      event.preventDefault();
    }
  }

  validateMaxValue() {
    if (this.form.data.allowedSpeed && parseInt(this.form.data.allowedSpeed) > 170) {
      this.form.data.allowedSpeed = '170'; // Reset to max allowed value
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
