import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class EndpointServiceService {

  constructor() { }

  public SERVER_URL = "http://localhost:8084";
  public MESSAGE = this.SERVER_URL + "/Message";
  public USER = this.SERVER_URL + "/User";
  public OWNER = this.SERVER_URL + "/Owner";
  public FIELD = this.SERVER_URL + "/Field";
  public SHOPPING = this.SERVER_URL + "/Shopping";
  public PAYMENT = this.SERVER_URL + "/Payment";
  public CLIENT = this.SERVER_URL + "/Client";
  public ATMS = this.SERVER_URL + "/ATMs";
  public CUSTOMER = this.SERVER_URL + "/Customer";
  public TRANSACTIONS = this.SERVER_URL + "/Transactions";
  public ROUTE = this.SERVER_URL + "/Route";
  public LOANS = this.SERVER_URL + "/Loans";
  public PROJECT = this.SERVER_URL + "/Project";
  public JOB = this.SERVER_URL + "/Job";
  public ISSUE = this.SERVER_URL + "/Issue";
  public PRIORITY = this.SERVER_URL + "/Priority";
  public EMPLOYEE = this.SERVER_URL + "/Employee";
  public TRANSPORTATION = this.SERVER_URL + "/Transportation";
  public COMPANY = this.SERVER_URL + "/Company";
  public ROLE = this.SERVER_URL + "/Role";
  public ORDER = this.SERVER_URL + "/Order";
  public SUPPLIER = this.SERVER_URL + "/Supplier";
  public INVENTORY = this.SERVER_URL + "/Inventory";
  public VEHICLETRACKING = this.SERVER_URL + "/VehicleTracking";
  public PRODUCTDETAILS = this.SERVER_URL + "/ProductDetails";
  public PRODUCT = this.SERVER_URL + "/Product";
  public PATIENT = this.SERVER_URL + "/Patient";
  public COLLEGE = this.SERVER_URL + "/College";
  public MARKSHEET = this.SERVER_URL + "/Marksheet";
  public STUDENT = this.SERVER_URL + "/Student";
  public SUBJECT = this.SERVER_URL + "/Subject";
  public FACULTY = this.SERVER_URL + "/Faculty";
  public COURSE = this.SERVER_URL + "/Course";
  public TIMETABLE = this.SERVER_URL + "/TimeTable";
  public JASPERREPORT = this.SERVER_URL + "/Jasper";

}
