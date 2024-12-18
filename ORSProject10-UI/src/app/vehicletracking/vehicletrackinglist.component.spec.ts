import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicletrackinglistComponent } from './vehicletrackinglist.component';

describe('VehicletrackinglistComponent', () => {
  let component: VehicletrackinglistComponent;
  let fixture: ComponentFixture<VehicletrackinglistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicletrackinglistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicletrackinglistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
