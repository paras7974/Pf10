import { Component, OnInit } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-transportationlist',
  templateUrl: './transportationlist.component.html',
  styleUrls: ['./transportationlist.component.css']
})
export class TransportationlistComponent extends BaseListCtl implements OnInit {

  public form = {
    error: false,
    message: null,
    preload: {
      modeList: [] // Initialize customer list
    },
    data: { id: null ,
      cost: ''
    },
    inputerror: { quantity: '', productName: '' },
    searchParams: {
      name: '',
      date: '', // Initialize date field
      modeId: null, // Initialize customerId to null
      cost: '',
      

    },
    searchMessage: null,
    list: [],
    pageNo: 0,
    

  };

  isValidCost: boolean = true;
  costErrorMessage: string = '';
  isValidDescription: boolean = true;
  descriptionErrorMessage: string = '';

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.TRANSPORTATION, locator, route);
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

   validateDescription(description: string) {
    const alphabeticPattern = /^[a-zA-Z]*$/;
    if (!alphabeticPattern.test(description)) {
      this.isValidDescription = false;
      this.descriptionErrorMessage = 'Description must contain only alphabetic characters.';
    } else {
      this.isValidDescription = true;
      this.descriptionErrorMessage = '';
    }
  }

  // Convert date to local format for display
  convertToLocalDate(dateString: string): string {
    const date = new Date(dateString);
    // Format date to 'MM/DD/YYYY'
    const options: any = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return date.toLocaleDateString(undefined, options);
  }


  validateNumericInput(event: KeyboardEvent) {
    const charCode = event.which || event.keyCode;
    const charStr = String.fromCharCode(charCode);

    // Regular expression to test if the character is a number
    const numberRegex = /^[0-9]+$/;

    // Test if the input matches the allowed characters and does not exceed the length
    if (!numberRegex.test(charStr) || this.form.data.cost.length >= 7) {
      event.preventDefault();
    }
  }

  validateMaxValue() {
    if (this.form.data.cost && parseInt(this.form.data.cost) > 5000000) {
      this.form.data.cost = '5000000'; // Reset to max allowed value
    }
  }
  
  parseDate(dateString: string): Date {
    if (dateString) {
      return new Date(dateString);
    }
    return null;
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
