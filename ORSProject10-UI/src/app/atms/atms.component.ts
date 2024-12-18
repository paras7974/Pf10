import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidatorFn } from '@angular/forms';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-atms',
  templateUrl: './atms.component.html',
  styleUrls: ['./atms.component.css']
})
export class AtmsComponent extends BaseCtl {

  getKey = false;
  selected = null;
  userForm: FormGroup = null;
  uploadForm: FormGroup;
  input

  constructor(public locator: ServiceLocatorService, private formBuilder: FormBuilder, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.ATMS, locator, route);
  }

  submit() {
    var _self = this;
    console.log('in submit');
    console.log(this.form);
    console.log(this.form.data);

    this.serviceLocator.httpService.post(this.api.save, this.form.data, function (res) {
      _self.form.message = '';
      _self.form.inputerror = {}; // Clear input errors here

      if (res.success) {
        _self.form.error = false; // Set error to false on success
        _self.form.message = "Data is saved";
        _self.form.data.id = res.result.data;
        console.log(_self.form.data.id);
        console.log("----------Rahul----------.");

        // Clear form data if needed
        // _self.form.data = {};

      } else {
        _self.form.error = true;
        if (res.result.inputerror) {
          _self.form.inputerror = res.result.inputerror;
        }
        _self.form.message = res.result.message;
      }
      console.log('FORM', _self.form);
    });
  }

  submit1() {
    var _self = this;
    console.log(this.form + "submit running start");
    console.log(this.form.data + "form data going to be submit");
    this.serviceLocator.httpService.post(this.api.search, this.form.data, function (res) {
      _self.form.message = '';
      _self.form.inputerror = {};
      _self.form.data.id = res.result.data;

      if (res.success) {
        _self.form.message = "Data is saved";
        _self.form.data.id = res.result.data;

        console.log(_self.form.data.id);
        console.log("--------------------.");

      } else {
        _self.form.error = true;
        _self.form.inputerror = res.result.inputerror;
        _self.form.message = res.result.message;
      }
      _self.form.data.id = res.result.data;
      console.log('FORM', _self.form);
    });
  }

  onUpload(userform: FormData) {
    this.submit();
    console.log(this.form.data.id + '---- after submit');
  }

  validateForm(form) {
    let flag = true;
    let validator = this.serviceLocator.dataValidator;
    flag = flag && validator.isNotNullObject(form.locationId);
    console.log(form.locationId);
    flag = flag && validator.isNotNullObject(form.cashAvailable);
    console.log(form.cashAvailable);
    flag = flag && validator.isNotNullObject(form.lastRestockedDate);
    console.log(form.lastRestockedDate);

    return flag;
  }

  populateForm(form, data) {
    form.id = data.id;
    console.log(form.id + 'populate form in shoppingcomponent');
    form.cashAvailable = data.cashAvailable;
    form.lastRestockedDate = data.lastRestockedDate;
    form.locationId = data.locationId;
  }

  validateCashAvailable(event: KeyboardEvent) {
    const input = event.key;
    // Allow numbers, decimal point, and backspace
    if (!(event.ctrlKey || event.altKey || event.metaKey) && (
      (input >= '0' && input <= '9') || input === '.' || input === 'Backspace' || input === 'Delete'
    )) {
      // Allow input to proceed
    } else {
      // Prevent default action (usually a character is typed)
      event.preventDefault();
    }
  }

  // Method to handle input validation for Cash Available field
  validateCashAvailableInput(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    let value = inputElement.value;

    // Remove non-numeric characters except decimal point
    value = value.replace(/[^0-9.]/g, '');

    // Remove extra decimal points
    const parts = value.split('.');
    if (parts.length > 2) {
      value = parts[0] + '.' + parts.slice(1).join('');
    }

    // Limit the value to 1,000,000,000
    const parsedValue = parseFloat(value);
    if (!isNaN(parsedValue) && parsedValue > 1000000000) {
      value = '1000000000';
    }

    // Update the input value
    inputElement.value = value;

    // Update the ngModel data
    this.form.data.cashAvailable = value;
  }


  parseDate(dateString: string): Date {
    if (dateString) {
      return new Date(dateString);
    }
    return null;
  }



}
