import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-transportation',
  templateUrl: './transportation.component.html',
  styleUrls: ['./transportation.component.css']
})
export class TransportationComponent extends BaseCtl {
  getKey = false;
  selected = null;
  userForm: FormGroup = null;
  uploadForm: FormGroup;

  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.TRANSPORTATION, locator, route);
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
        console.log("----------Paras----------.");

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

  validatePaymentTerm() {
    const paymentTerm = this.form.data.paymentTerm;
    const paymentTermPattern = /^[a-zA-Z ]*$/; // Only allows alphabetic characters and spaces
    if (!paymentTermPattern.test(paymentTerm)) {
      this.form.inputerror.paymentTerm = 'Invalid payment term';
    } else {
      delete this.form.inputerror.paymentTerm;
    }
  }

  validateForm(form) {
    let flag = true;
    let validator = this.serviceLocator.dataValidator;
    flag = flag && validator.isNotNullObject(form.modeId);
    console.log(form.modeId);
    flag = flag && validator.isNotNullObject(form.description);
    console.log(form.description);
    flag = flag && validator.isNotNullObject(form.cost);
    console.log(form.cost);
    flag = flag && validator.isNotNullObject(form.date);
    console.log(form.date);

    return flag;
  }

  populateForm(form, data) {
    form.id = data.id;
    console.log(form.id + 'populate form in shoppingcomponent');
    form.description = data.description;
    form.cost = data.cost;
    form.date = data.date;
    form.modeId = data.modeId;
  }

  validateAlphabetInput(event: KeyboardEvent) {
    const charCode = event.which || event.keyCode;
    const charStr = String.fromCharCode(charCode);

    // Regular expression to test if the character is a letter
    const letterRegex = /^[a-zA-Z\s]+$/;

    // Test if the input matches the allowed characters
    if (!letterRegex.test(charStr)) {
      event.preventDefault();
    }
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

  test() {
    // Implement your test logic here
  }
}
