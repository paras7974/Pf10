import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductdetailslistComponent } from './productdetailslist.component';

describe('ProductdetailslistComponent', () => {
  let component: ProductdetailslistComponent;
  let fixture: ComponentFixture<ProductdetailslistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductdetailslistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductdetailslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
