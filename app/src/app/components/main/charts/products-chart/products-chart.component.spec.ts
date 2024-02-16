import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsChartComponent } from './products-chart.component';

describe('ProductsChartComponent', () => {
  let component: ProductsChartComponent;
  let fixture: ComponentFixture<ProductsChartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductsChartComponent]
    });
    fixture = TestBed.createComponent(ProductsChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
