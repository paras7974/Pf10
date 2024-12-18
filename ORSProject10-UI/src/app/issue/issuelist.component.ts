import { Component, OnInit } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-issuelist',
  templateUrl: './issuelist.component.html',
  styleUrls: ['./issuelist.component.css']
})
export class IssuelistComponent extends BaseListCtl implements OnInit {

  public form = {
    error: false,
    message: null,
    preload: {
      statusList: [] // Initialize customer list
    },
    data: { id: null },
    inputerror: { quantity: '', productName: '' },
    searchParams: {
      title: '',
      description: '',
      date: '', // Initialize date field
      statusId: null, // Initialize customerId to null
      assignTO: '',
      openDate: ''
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
  isValidDescription: boolean = true;
  descriptionErrorMessage: string = '';

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.ISSUE, locator, route);
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
    const options:any = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return date.toLocaleDateString(undefined, options);
  }

  // Validate input for name, mobile, and allowedSpeed fields
  validateInput(event: any, field: string) {
    const value = event.target.value;
    if (field === 'title' || field === 'description') {
      const regex = /^[A-Za-z\s]*$/; // Updated regex to ensure no numbers
      if (!regex.test(value)) {
        if (field === 'title') {
          this.isValidNameInput = false;
          this.nameErrorMessage = 'Please type alphabets only, numbers are not allowed';
        } else if (field === 'description') {
          this.isValidDescription = false;
          this.descriptionErrorMessage = 'Description must contain only alphabetic characters, numbers are not allowed.';
        }
      } else {
        if (field === 'title') {
          this.isValidNameInput = true;
          this.nameErrorMessage = '';
        } else if (field === 'description') {
          this.isValidDescription = true;
          this.descriptionErrorMessage = '';
        }
      }
    }
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
