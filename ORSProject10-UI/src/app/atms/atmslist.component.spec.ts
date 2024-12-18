import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AtmslistComponent } from './atmslist.component';

describe('AtmslistComponent', () => {
  let component: AtmslistComponent;
  let fixture: ComponentFixture<AtmslistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AtmslistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AtmslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
