import { Component, OnInit } from '@angular/core';
import { BaseListCtl } from '../base-list.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-orderlist',
  templateUrl: './orderlist.component.html',
  styleUrls: ['./orderlist.component.css']
})
export class OrderlistComponent extends BaseListCtl implements OnInit {

  public form = {
    error: false,
    message: null,
    preload: {
      customerList: [] // Initialize customer list
    },
    data: { id: null },
    inputerror: { quantity: '', productName: '' },
    searchParams: {
      quantity: '',
      date: '', // Initialize date field
      customerId: null, // Initialize customerId to null
      productName: '',
      orderDate: ''
    },
    searchMessage: null,
    list: [],
    pageNo: 0
  };

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.ORDER, locator, route);
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

  // Validate input for quantity field
  validateQuantity() {
    const quantity = this.form.searchParams.quantity;
    if (quantity === null || isNaN(Number(quantity)) || Number(quantity) <= 0 || Number(quantity) > 1000) {
      this.form.inputerror.quantity = 'Invalid quantity. Please enter a number between 1 and 1000.';
    } else {
      this.form.inputerror.quantity = ''; // Clear error message if quantity is valid
    }
  }

  // Clear quantity input error message when field is cleared
  clearQuantityError() {
    if (!this.form.searchParams.quantity) {
      this.form.inputerror.quantity = '';
    }
  }

  validateProductName(event: any) {
    const value = event.target.value.trim();
    const regex = /^[a-zA-Z]{1,20}$/; // Alphabetic characters only, length between 3 and 20

    if (value === '') {
      this.form.inputerror.productName = null; // Clear the error message if the field is empty
    } else if (!regex.test(value)) {
      this.form.inputerror.productName = 'Product Name should contain only alphabetic characters and be between 3 to 20 characters long';
    } else {
      this.form.inputerror.productName = null;
      this.form.searchParams.productName = value;
    }
  }

  // Clear productName error on input field focus
  clearProductNameError() {
    this.form.inputerror.productName = null;
  }

  // Submit method
  submit() {
    // Reset page number before searching
    this.form.pageNo = 0;

    // Check if there are any input errors
    if (this.form.inputerror.quantity || this.form.inputerror.productName) {
      return; // Do not proceed with search if there are errors
    }

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
