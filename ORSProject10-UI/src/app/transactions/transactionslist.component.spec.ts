import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionslistComponent } from './transactionslist.component';

describe('TransactionslistComponent', () => {
  let component: TransactionslistComponent;
  let fixture: ComponentFixture<TransactionslistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransactionslistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
