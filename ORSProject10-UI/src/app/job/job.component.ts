import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-job',
  templateUrl: './job.component.html',
  styleUrls: ['./job.component.css']
})
export class JobComponent extends BaseCtl {

  getKey = false;
  selected = null;
  userForm: FormGroup = null;
  uploadForm: FormGroup;
  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.JOB, locator, route);
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
    flag = flag && validator.isNotNullObject(form.statusId);
    console.log(form.statusId);
    flag = flag && validator.isNotNullObject(form.title);
    console.log(form.title);
    flag = flag && validator.isNotNullObject(form.experience);
    console.log(form.experience);
    flag = flag && validator.isNotNullObject(form.dateOfOpening);
    console.log(form.dateOfOpening);

    return flag;
  }

  populateForm(form, data) {
    form.id = data.id;
    console.log(form.id + 'populate form in shoppingcomponent');
    form.title = data.title;
    form.experience = data.experience;
    form.dateOfOpening = data.dateOfOpening;
    form.statusId = data.statusId;


  }

  validatePhone(event: KeyboardEvent) {
    const input = event.key;
    // Check if the input is a number or backspace
    if ((isNaN(Number(input)) && input !== 'Backspace') || (input === ' ')) {
      event.preventDefault();
    }
    // Limit the input to 10 characters
    if (this.form.data.phone && this.form.data.phone.length >= 10 && input !== 'Backspace') {
      event.preventDefault();
    }
  }

  validateAlphabetInput(event) {
    const charCode = event.which || event.keyCode;
    const charStr = String.fromCharCode(charCode);

    // Regular expression to test if the character is a letter
    const letterRegex = /^[a-zA-Z\s]+$/;

    // Test if the input matches the allowed characters
    if (!letterRegex.test(charStr)) {
      event.preventDefault();
    }

    // Optionally, check the entire value against the name format regex
    const inputElement = event.target;
    const inputValue = inputElement.value + charStr; // Add the current character to the input value

    // Regex for valid name format "FirstName LastName"
    const nameRegex = /^[A-Z][a-z]+ [A-Z][a-z]+$/;

    // Check if the current value matches the valid name format
    if (!nameRegex.test(inputValue)) {
      // Handle invalid input (e.g., disable submit button, show error message)
      // Example:
      inputElement.classList.add('invalid'); // Apply CSS class for invalid input
    } else {
      inputElement.classList.remove('invalid'); // Remove invalid CSS class if format is valid
    }
  }




  parseDate(dateString: string): Date {
    if (dateString) {
      return new Date(dateString);
    }
    return null;
  }
  test() {

  }

}
