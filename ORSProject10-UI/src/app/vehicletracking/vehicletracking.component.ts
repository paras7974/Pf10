import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { BaseCtl } from '../base.component';
import { ServiceLocatorService } from '../service-locator.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-vehicletracking',
  templateUrl: './vehicletracking.component.html',
  styleUrls: ['./vehicletracking.component.css']
})
export class VehicletrackingComponent extends BaseCtl {

  getKey = false;
  selected = null;
  userForm: FormGroup = null;
  uploadForm: FormGroup;
  constructor(public locator: ServiceLocatorService, public route: ActivatedRoute, private httpClient: HttpClient) {
    super(locator.endpoints.VEHICLETRACKING, locator, route);
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
    flag = flag && validator.isNotNullObject(form.vehicleIdId);
    console.log(form.vehicleIdId);
    flag = flag && validator.isNotNullObject(form.name);
    console.log(form.name);
    flag = flag && validator.isNotNullObject(form.lat);
    console.log(form.lat);
    flag = flag && validator.isNotNullObject(form.longitude);
    console.log(form.longitude);
    flag = flag && validator.isNotNullObject(form.date);
    console.log(form.date);

    return flag;
  }

  populateForm(form, data) {
    form.id = data.id;
    console.log(form.id + 'populate form in shoppingcomponent');
    form.name = data.name;
    form.lat = data.lat;
    form.longitude = data.longitude;
    form.date = data.date;
    form.vehicleIdId = data.vehicleIdId;


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
