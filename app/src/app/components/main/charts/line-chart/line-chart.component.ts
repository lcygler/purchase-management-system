import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css'],
})
export class LineChartComponent implements OnInit {
  public chart: any;

  ngOnInit(): void {
    this.createChart();
  }

  createChart() {
    const months = [
      'Ene',
      'Feb',
      'Mar',
      'Abr',
      'May',
      'Jun',
      'Jul',
      'Ago',
      'Sep',
      'Oct',
      'Nov',
      'Dic',
    ];

    const amounts = [
      500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600,
    ];

    this.chart = new Chart('line-chart', {
      type: 'line',
      data: {
        labels: months,
        datasets: [
          {
            label: 'Total',
            data: amounts,
            fill: false,
            borderColor: 'rgba(255, 99, 132, 1)',
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
