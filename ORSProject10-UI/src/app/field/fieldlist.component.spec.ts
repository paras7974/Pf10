import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FieldlistComponent } from './fieldlist.component';

describe('FieldlistComponent', () => {
  let component: FieldlistComponent;
  let fixture: ComponentFixture<FieldlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FieldlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FieldlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
