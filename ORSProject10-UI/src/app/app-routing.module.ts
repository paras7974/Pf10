
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MarksheetComponent } from './marksheet/marksheet.component';
import { StudentComponent } from './student/student.component';
import { CollegeComponent } from './college/college.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ForgotPasswordComponent } from './login/forgotpassword.component';
import { SignUpComponent } from './login/signup.component';
import { MessageComponent } from './message/message.component';
import { MessageListComponent } from './message/message-list.component';
import { MarksheetListComponent } from './marksheet/marksheet-list.component';
import { CollegeListComponent } from './college/college-list.component';
import { StudentListComponent } from './student/student-list.component';
import { UserListComponent } from './user/user-list.component';
import { UserComponent } from './user/user.component';
import { RoleComponent } from './role/role.component';
import { CourseComponent } from "./course/course.component";
import { SubjectComponent } from "./subject/subject.component"
import { Subject, from } from 'rxjs';
import { RoleListComponent } from './role/role-list.component';
import { CourseListComponent } from "./course/course-list.component";
import { FacultyComponent } from "./faculty/faculty.component";
import { FacultyListComponent } from "./faculty/faculty-list.component";
import { TimetableComponent } from "./timetable/timetable.component";
import { FileComponent } from './file/file.component';
import { TimetableListComponent } from './timetable/timetable-list.component';
import { SubjectListComponent } from './subject/subject-list.component';
import { MarksheetmeritListComponent } from "./marksheet/marksheetmerit-list.component";
import { GetmarksheetComponent } from "./marksheet/getmarksheet.component";
import { ChangepasswordComponent } from "./user/changepassword.component";
import { LoaderComponent } from './loader/loader.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { MyprofileComponent } from './user/myprofile.component';
import { OrderComponent } from './order/order.component';
import { OrderlistComponent } from './order/orderlist.component';

import { ProductComponent } from './product/product.component';
import { ProductlistComponent } from './product/productlist.component';
import { ProductdetailsComponent } from './productdetails/productdetails.component';
import { ProductdetailslistComponent } from './productdetails/productdetailslist.component';
import { PatientComponent } from './patient/patient.component';
import { PatientlistComponent } from './patient/patientlist.component';
import { CompanyComponent } from './company/company.component';
import { CompanylistComponent } from './company/companylist.component';
import { InventoryComponent } from './inventory/inventory.component';
import { InventorylistComponent } from './inventory/inventorylist.component';
import { SupplierComponent } from './supplier/supplier.component';
import { SupplierlistComponent } from './supplier/supplierlist.component';
import { VehicletrackingComponent } from './vehicletracking/vehicletracking.component';
import { VehicletrackinglistComponent } from './vehicletracking/vehicletrackinglist.component';
import { TransportationComponent } from './transportation/transportation.component';
import { TransportationlistComponent } from './transportation/transportationlist.component';
import { RouteComponent } from './route/route.component';
import { RoutelistComponent } from './route/routelist.component';
import { IssueComponent } from './issue/issue.component';
import { IssuelistComponent } from './issue/issuelist.component';
import { EmployeeComponent } from './employee/employee.component';
import { EmployeelistComponent } from './employee/employeelist.component';
import { JobComponent } from './job/job.component';
import { JoblistComponent } from './job/joblist.component';
import { CustomerComponent } from './customer/customer.component';
import { CustomerlistComponent } from './customer/customerlist.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { TransactionslistComponent } from './transactions/transactionslist.component';
import { LoansComponent } from './loans/loans.component';
import { LoanslistComponent } from './loans/loanslist.component';
import { AtmsComponent } from './atms/atms.component';
import { AtmslistComponent } from './atms/atmslist.component';
import { ProjectComponent } from './project/project.component';
import { ProjectlistComponent } from './project/projectlist.component';
import { OwnerComponent } from './owner/owner.component';
import { OwnerlistComponent } from './owner/ownerlist.component';
import { FieldComponent } from './field/field.component';
import { FieldlistComponent } from './field/fieldlist.component';


const routes: Routes = [
    {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
    },
    {
        path: 'dashboard',
        component: DashboardComponent
    },
    {
        path: 'loader',
        component: LoaderComponent

    },
    {
        path: 'login',
        component: LoginComponent
    }
    ,
    {
        path: 'login/:userparams',
        component: LoginComponent
    },

    {
        path: 'spinner',
        component: SpinnerComponent
    },
    {
        path: 'forgotpassword',
        component: ForgotPasswordComponent
    },
    {
        path: 'signup',
        component: SignUpComponent
    },
    {
        path: 'message',
        component: MessageComponent,

    },
    {
        path: 'message/:id',
        component: MessageComponent
    },
    {
        path: 'messagelist',
        component: MessageListComponent

    },
    {
        path: 'marksheet',
        component: MarksheetComponent

    },
    {
        path: 'marksheet/:id',
        component: MarksheetComponent

    },
    {
        path: 'marksheetlist',
        component: MarksheetListComponent

    },
    {
        path: 'marksheetmeritlist',
        component: MarksheetmeritListComponent

    },
    {
        path: 'getmarksheet',
        component: GetmarksheetComponent

    },
    {
        path: 'college',
        component: CollegeComponent

    },
    {
        path: 'college/:id',
        component: CollegeComponent

    },
    {
        path: 'collegelist',
        component: CollegeListComponent

    },
    {
        path: 'student',
        component: StudentComponent

    },
    {
        path: 'student/:id',
        component: StudentComponent

    },
    {
        path: 'studentlist',
        component: StudentListComponent

    },
    {
        path: 'user',
        component: UserComponent

    },
    {
        path: 'user/:id',
        component: UserComponent

    },
    {
        path: 'userlist',
        component: UserListComponent

    },
    {
        path: 'role',
        component: RoleComponent

    },
    {
        path: 'rolelist',
        component: RoleListComponent

    },
    {
        path: 'role/:id',
        component: RoleComponent

    },
    {
        path: 'course',
        component: CourseComponent

    },
    {
        path: 'courselist',
        component: CourseListComponent

    },
    {
        path: 'course/:id',
        component: CourseComponent

    },
    {

        path: 'faculty',
        component: FacultyComponent

    },
    {
        path: 'facultylist',
        component: FacultyListComponent

    },
    {
        path: 'faculty/:id',
        component: FacultyComponent

    },
    {
        path: 'timetable',
        component: TimetableComponent


    },
    {
        path: 'timetablelist',
        component: TimetableListComponent

    },
    {
        path: 'timetable/:id',
        component: TimetableComponent

    },
    {
        path: 'subject',
        component: SubjectComponent

    },
    {
        path: 'subjectlist',
        component: SubjectListComponent

    },
    {
        path: 'subject/:id',
        component: SubjectComponent

    },
    {
        path: 'myprofile',
        component: MyprofileComponent
    },
    {
        path: 'file',
        component: FileComponent
    },
    {
        path: 'changepassword',
        component: ChangepasswordComponent


    },
    {
        path: 'order',
        component: OrderComponent

    },
    {
        path: 'order/:id',
        component: OrderComponent

    },
    {
        path: 'orderlist',
        component: OrderlistComponent

    },
    {
        path: 'owner',
        component: OwnerComponent

    },
    {
        path: 'owner/:id',
        component: OwnerComponent

    },
    {
        path: 'ownerlist',
        component: OwnerlistComponent

    },

    {
        path: 'field',
        component: FieldComponent

    },
    {
        path: 'field/:id',
        component: FieldComponent

    },
    {
        path: 'fieldlist',
        component: FieldlistComponent

    },


    {
        path: 'productdetails',
        component: ProductdetailsComponent

    },
    {
        path: 'productdetails/:id',
        component: ProductdetailsComponent

    },
    {
        path: 'productdetailslist',
        component: ProductdetailslistComponent

    },


    {
        path: 'patient',
        component: PatientComponent

    },
    {
        path: 'patient/:id',
        component: PatientComponent

    },
    {
        path: 'patientlist',
        component: PatientlistComponent

    },

    {
        path: 'inventory',
        component: InventoryComponent

    },
    {
        path: 'inventory/:id',
        component: InventoryComponent

    },
    {
        path: 'inventorylist',
        component: InventorylistComponent

    },

    {
        path: 'supplier',
        component: SupplierComponent

    },
    {
        path: 'supplier/:id',
        component: SupplierComponent

    },
    {
        path: 'supplierlist',
        component: SupplierlistComponent

    },

    {
        path: 'vehicletracking',
        component: VehicletrackingComponent

    },
    {
        path: 'vehicletracking/:id',
        component: VehicletrackingComponent

    },
    {
        path: 'vehicletrackinglist',
        component: VehicletrackinglistComponent

    },

    {
        path: 'company',
        component: CompanyComponent

    },
    {
        path: 'company/:id',
        component: CompanyComponent

    },
    {
        path: 'companylist',
        component: CompanylistComponent

    },

    {
        path: 'transportation',
        component: TransportationComponent

    },
    {
        path: 'transportation/:id',
        component: TransportationComponent

    },
    {
        path: 'transportationlist',
        component: TransportationlistComponent

    },


    {
        path: 'employee',
        component: EmployeeComponent

    },
    {
        path: 'employee/:id',
        component: EmployeeComponent

    },
    {
        path: 'employeelist',
        component: EmployeelistComponent

    },

    {
        path: 'job',
        component: JobComponent

    },
    {
        path: 'job/:id',
        component: JobComponent

    },
    {
        path: 'joblist',
        component: JoblistComponent

    },

    {
        path: 'customer',
        component: CustomerComponent

    },
    {
        path: 'customer/:id',
        component: CustomerComponent

    },
    {
        path: 'customerlist',
        component: CustomerlistComponent

    },

    {
        path: 'transactions',
        component: TransactionsComponent

    },
    {
        path: 'transactions/:id',
        component: TransactionsComponent

    },
    {
        path: 'transactionslist',
        component: TransactionslistComponent

    },

    {
        path: 'issue',
        component: IssueComponent

    },
    {
        path: 'issue/:id',
        component: IssueComponent

    },
    {
        path: 'issuelist',
        component: IssuelistComponent

    },

    {
        path: 'route',
        component: RouteComponent

    },
    {
        path: 'route/:id',
        component: RouteComponent

    },
    {
        path: 'routelist',
        component: RoutelistComponent

    },

    {
        path: 'atms',
        component: AtmsComponent

    },
    {
        path: 'atms/:id',
        component: AtmsComponent

    },
    {
        path: 'atmslist',
        component: AtmslistComponent

    },


    {
        path: 'project',
        component: ProjectComponent

    },
    {
        path: 'project/:id',
        component: ProjectComponent

    },
    {
        path: 'projectlist',
        component: ProjectlistComponent

    },


    {
        path: 'loans',
        component: LoansComponent

    },
    {
        path: 'loans/:id',
        component: LoansComponent

    },
    {
        path: 'loanslist',
        component: LoanslistComponent

    },



    {
        path: 'product',
        component: ProductComponent

    },
    {
        path: 'productlist',
        component: ProductlistComponent

    },
    {
        path: 'product/:id',
        component: ProductComponent

    },







];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})


export class AppRoutingModule { }