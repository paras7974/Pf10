import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent extends BaseCtl {

  getKey = false;
  selected = null;
  userForm: FormGroup = null;
  uploadForm: FormGroup;

  constructor(public locator: ServiceLocatorService, private formBuilder: FormBuilder, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.ORDER, locator, route);
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
    flag = flag && validator.isNotNullObject(form.customerId);
    console.log(form.customerId);
    flag = flag && validator.isNotNullObject(form.quantity);
    console.log(form.quantity);
    flag = flag && validator.isNotNullObject(form.productName);
    console.log(form.productName);
    flag = flag && validator.isNotNullObject(form.orderDate);
    console.log(form.orderDate);

    return flag;
  }

  populateForm(form, data) {
    form.id = data.id;
    console.log(form.id + 'populate form in shoppingcomponent');
    form.productName = data.productName;
    form.quantity = data.quantity;
    form.orderDate = data.orderDate;
    form.customerId = data.customerId;
  }

  validateProductName(event: KeyboardEvent) {
    const input = event.target as HTMLInputElement;
    const pattern = /^[a-zA-Z]*$/;
    const currentValue = input.value;
    const key = event.key;

    if (!pattern.test(key)) {
      event.preventDefault();
    }

    // Ensure the total length is between 3 and 20
    if (currentValue.length + 1 > 20) {
      event.preventDefault();
    }
  }

  validateQuantity(event: KeyboardEvent) {
    const input = event.target as HTMLInputElement;
    const pattern = /^[0-9]*$/;
    const key = event.key;

    // Allow numbers only
    if (!pattern.test(key)) {
      event.preventDefault();
    }

    // Ensure the value is between 1 and 1000
    const currentValue = input.value + key;
    if (parseInt(currentValue) > 1000) {
      event.preventDefault();
    }
  }

  parseDate(dateString: string): Date {
    if (dateString) {
      return new Date(dateString);
    }
    return null;
  }


}
