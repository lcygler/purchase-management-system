import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import { OrderService } from 'src/app/services/order/order.service';
import { MonthTotal } from 'src/app/types/TMonthTotal';

@Component({
  selector: 'app-orders-chart',
  templateUrl: './orders-chart.component.html',
  styleUrls: ['./orders-chart.component.css'],
})
export class OrdersChartComponent implements OnInit {
  public chart: any;
  monthsTotal: MonthTotal[] = [];

  constructor(private orderService: OrderService) {}

  ngOnInit() {
    this.getMonthsTotal();
  }

  getMonthsTotal() {
    this.orderService.getMonthsTotal().subscribe((res) => {
      this.monthsTotal = res;
      this.createChart();
    });
  }

  createChart() {
    const months = this.monthsTotal.map((month) => `${month[0]}/${month[1]}`);
    const amounts = this.monthsTotal.map((month) => month[2]);

    this.chart = new Chart('orders-chart', {
      type: 'line',
      data: {
        labels: months,
        datasets: [
          {
            label: 'Total',
            data: amounts,
            fill: true,
            borderColor: '#393e46',
            tension: 0.4,
          },
        ],
      },
      options: {
        aspectRatio: 2,
        plugins: {
          legend: {
            display: false,
          },
        },
      },
    });
  }
}
