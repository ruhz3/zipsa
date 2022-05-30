<template>
  <!-- <canvas
        class="statistics-charts-line"
        ref="lineChart"
        width="500"
        height="400"
      ></canvas> -->
  <div style="width: 500px; height: 500px">
    <canvas id="bookmark-chart"></canvas>
  </div>
</template>
<script>
import Chart from "chart.js/auto";
import { getChartData } from "@/api/bookmark.js";

export default {
  name: "BookmarkChart",

  data() {
    return {
      bookmarkChartData: {},
      chartData: {},
    };
  },
  created() {
    getChartData(
      // this.$route.params.aptCode,
      11,
      (response) => {
        console.log(response);
        this.bookmarkChartData = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  mounted() {
    const ctx = document.getElementById("bookmark-chart");
    new Chart(ctx, {
      type: "radar",
      // data: {
      //   labels: ["1", "2", "3", "4", "5"],
      //   // data: Object.values(this.bookmarkChartData),
      data: {
        labels: ["최대면적", "최근거래가", "준공년도", "매매건수", "최고층"],
        datasets: [
          {
            label: "금호자이1차",
            data: [65, 59, 90, 81, 56],
            fill: true,
            backgroundColor: "rgba(255, 99, 132, 0.2)",
            borderColor: "rgb(255, 99, 132)",
            pointBackgroundColor: "rgb(255, 99, 132)",
            pointBorderColor: "#fff",
            pointHoverBackgroundColor: "#fff",
            pointHoverBorderColor: "rgb(255, 99, 132)",
          },
          {
            label: "두산",
            data: [28, 48, 40, 19, 96],
            fill: true,
            backgroundColor: "rgba(54, 162, 235, 0.2)",
            borderColor: "rgb(54, 162, 235)",
            pointBackgroundColor: "rgb(54, 162, 235)",
            pointBorderColor: "#fff",
            pointHoverBackgroundColor: "#fff",
            pointHoverBorderColor: "rgb(54, 162, 235)",
          },
          {
            label: "SM해그린",
            data: [40, 25, 55, 80, 74],
            fill: true,
            backgroundColor: "rgba(0, 204, 102, 0.2)",
            borderColor: "rgb(0, 204, 102)",
            pointBackgroundColor: "rgb(0, 204, 102)",
            pointBorderColor: "#fff",
            pointHoverBackgroundColor: "#fff",
            pointHoverBorderColor: "rgb(0, 204, 102)",
          },
        ],
      },
      // fill: true,
      options: {
        elements: {
          line: {
            borderWidth: 3,
          },
        },
      },
    });
  },
};
</script>
