import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicletrackingComponent } from './vehicletracking.component';

describe('VehicletrackingComponent', () => {
  let component: VehicletrackingComponent;
  let fixture: ComponentFixture<VehicletrackingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicletrackingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicletrackingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
