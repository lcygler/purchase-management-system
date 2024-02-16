import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import ChartDataLabels from 'chartjs-plugin-datalabels';

@Component({
  selector: 'app-suppliers-chart',
  templateUrl: './suppliers-chart.component.html',
  styleUrls: ['./suppliers-chart.component.css'],
})
export class SuppliersChartComponent implements OnInit {
  public chart: any;

  ngOnInit(): void {
    this.createChart();
  }

  createChart() {
    this.chart = new Chart('suppliers-chart', {
      type: 'bar',
      data: {
        labels: ['Proveedores', 'Productos', 'Órdenes'],
        datasets: [
          {
            label: 'Cantidad',
            data: ['400', '600', '500'],
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
            ],
          },
        ],
      },
      plugins: [ChartDataLabels],
      options: {
        aspectRatio: 2.5,
        plugins: {
          legend: {
            display: false,
          },
        },
      },
    });
  }
}
