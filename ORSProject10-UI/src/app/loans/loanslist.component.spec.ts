import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanslistComponent } from './loanslist.component';

describe('LoanslistComponent', () => {
  let component: LoanslistComponent;
  let fixture: ComponentFixture<LoanslistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoanslistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoanslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
