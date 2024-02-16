import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuppliersChartComponent } from './suppliers-chart.component';

describe('SuppliersChartComponent', () => {
  let component: SuppliersChartComponent;
  let fixture: ComponentFixture<SuppliersChartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SuppliersChartComponent]
    });
    fixture = TestBed.createComponent(SuppliersChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
