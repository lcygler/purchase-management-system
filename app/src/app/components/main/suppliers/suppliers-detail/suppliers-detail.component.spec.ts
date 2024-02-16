import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuppliersDetailComponent } from './suppliers-detail.component';

describe('SuppliersDetailComponent', () => {
  let component: SuppliersDetailComponent;
  let fixture: ComponentFixture<SuppliersDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SuppliersDetailComponent]
    });
    fixture = TestBed.createComponent(SuppliersDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
